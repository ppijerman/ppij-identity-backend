package org.ppijerman.ppijidentitybackend.server.service.security.crypto;

import org.bouncycastle.bcpg.HashAlgorithmTags;
import org.bouncycastle.openpgp.*;
import org.bouncycastle.openpgp.bc.BcPGPObjectFactory;
import org.bouncycastle.openpgp.operator.PBESecretKeyDecryptor;
import org.bouncycastle.openpgp.operator.bc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Calendar;
import java.util.Iterator;
import java.util.UUID;

@Component
public class CryptoService implements PasswordEncoder {

    private final static Logger log = LoggerFactory.getLogger(CryptoService.class);

    private final BytesEncryptor bytesEncryptor;
    private final int HASH_ROUNDS;
    private final PGPSecretKeyRingCollection pgpSecretKeyRingCollection;
    private final String pgpKeyPassphrase;
    private final String hashAlgorithm;

    public CryptoService(
        @Value("${ppij-id.security.encryption.private-key}") String privateKey,
        @Value("${ppij-id.security.encryption.hash-rounds:10}") int hashRounds,
        @Value("${ppij-id.security.encryption.pgp.secret-key}") String pathToSecretKey,
        @Value("${ppij-id.security.encryption.pgp.key-passphrase:#{null}}") String pgpKeyPassphrase,
        @Value("${ppij-id.security.encryption.pgp.hash-algorithm:SHA256}") String hashAlgorithm
    ) throws IOException, PGPException {
        if (hashRounds > 30) {
            log.warn("Number of iteration is considered too big ({}/{}).", hashRounds, 30);
        }
        this.bytesEncryptor = Encryptors.stronger(privateKey, KeyGenerators.string().generateKey());
        this.HASH_ROUNDS = hashRounds;
        this.pgpKeyPassphrase = pgpKeyPassphrase;
        this.hashAlgorithm = hashAlgorithm;
        final ClassPathResource secretKeyPathResource = new ClassPathResource(pathToSecretKey);

        try (
                InputStream secretKeyInputStream = new FileInputStream(secretKeyPathResource.getFile());
                InputStream pgpInputStream = PGPUtil.getDecoderStream(secretKeyInputStream)
        ) {
            this.pgpSecretKeyRingCollection = new PGPSecretKeyRingCollection(pgpInputStream, new BcKeyFingerprintCalculator());

            pgpSecretKeyRingCollection.getKeyRings().forEachRemaining(pgpSecretKeys -> {
                log.info("Loaded secret key for user {} with fingerprint {}",
                        convertUserIds(pgpSecretKeys.getSecretKey().getUserIDs()),
                        convertFingerprint(pgpSecretKeys.getPublicKey().getFingerprint()));
            });
        }
    }

    @Override
    public String encode(final CharSequence rawPassword) {
        return this.hash(rawPassword.toString());
    }

    @Override
    public boolean matches(final CharSequence rawPassword, String encodedPassword) {
        return this.encode(rawPassword).equals(encodedPassword);
    }

    public String hash(final String plainText) {
        return BCrypt.hashpw(plainText, BCrypt.gensalt(HASH_ROUNDS));
    }

    public boolean checkHash(final String plainText, final String hashed) {
        return BCrypt.checkpw(plainText, hashed);
    }

    public byte[] encrypt(final Serializable serializable) {
        try (
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)
        ) {
            objectOutputStream.writeObject(serializable);
            objectOutputStream.flush();
            byte[] readBytes = byteArrayOutputStream.toByteArray();
            return this.encryptBytes(readBytes);
        } catch (IOException e) {
            log.error("IOException happen when encrypting serializable object with message: {}", e.getMessage());
            return null;
        }
    }

    public byte[] encryptBytes(final byte[] plainBytes) {
        return bytesEncryptor.encrypt(plainBytes);
    }

    public byte[] encryptString(final String plainText) {
        return this.encryptBytes(plainText.getBytes(StandardCharsets.UTF_8));
    }

    public byte[] encryptInt(final int plainInt) {
        return this.encryptString(plainInt + "");
    }

    public byte[] encryptUUID(final UUID plainUuid) {
        return this.encryptString(plainUuid.toString());
    }

    public byte[] encryptCalendar(final Calendar calendar) {
        return this.encryptString(calendar.toInstant().toString());
    }

    public byte[] decryptToBytes(final byte[] cipherBytes) {
        return bytesEncryptor.decrypt(cipherBytes);
    }

    public String decryptToString(final byte[] cipherBytes) {
        return new String(this.decryptToBytes(cipherBytes));
    }

    public int decryptToInt(final byte[] cipherBytes) {
        return Integer.parseInt(this.decryptToString(cipherBytes));
    }

    public UUID decryptToUUID(final byte[] cipherBytes) {
        return UUID.fromString(this.decryptToString(cipherBytes));
    }

    public Calendar decryptToCalendar(final byte[] cipherBytes) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Instant.parse(this.decryptToString(cipherBytes)).toEpochMilli());
        return calendar;
    }

    public byte[] sign(final int signatureType, final byte[] plainBytes, final String signerId) {
        try {
            PGPSecretKey pgpSecretKey = getSigningSecretKeyForId(signerId);

            if (pgpSecretKey == null) {
                log.error(String.format("Cannot find signing key for user id %s!", signerId));
                return null;
            }

            final PBESecretKeyDecryptor secretKeyDecryptor = new BcPBESecretKeyDecryptorBuilder(new BcPGPDigestCalculatorProvider()).build(pgpKeyPassphrase.toCharArray());
            final PGPPrivateKey pgpPrivateKey = pgpSecretKey.extractPrivateKey(secretKeyDecryptor);

            int hashAlgorithmInt = HashAlgorithmTags.class.getField(hashAlgorithm).getInt(HashAlgorithmTags.class);
            PGPSignatureGenerator pgpSignatureGenerator = new PGPSignatureGenerator(new BcPGPContentSignerBuilder(pgpSecretKey.getPublicKey().getAlgorithm(), hashAlgorithmInt));
            pgpSignatureGenerator.init(signatureType, pgpPrivateKey);
            pgpSignatureGenerator.update(plainBytes);

            return pgpSignatureGenerator.generate().getEncoded(false);
        } catch (PGPException e) {
            log.error("Got PGPException when signing with message: {}", e.getMessage());
        } catch (IOException e) {
            log.error("Got IOException when signing with message: {}", e.getMessage());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            log.error("Got Exception when trying to access field in interface with message: {}", e.getMessage());
        }
        return null;
    }

    public boolean verify(final byte[] signatureBytes, final byte[] signedBytes, final String signerId) {
        try {
            PGPSecretKey pgpSecretKey = getSigningSecretKeyForId(signerId);

            if (pgpSecretKey == null) {
                log.error("Cannot find signing key for user id {}!", signerId);
                return false;
            }

            PGPSignature signature = extractSignature(signatureBytes);

            if (signature == null) {
                log.error("Cannot find and verify signature!");
                return false;
            }

            signature.init(new BcPGPContentVerifierBuilderProvider(), pgpSecretKey.getPublicKey());
            signature.update(signedBytes);

            return signature.verify();
        } catch (PGPException e) {
            log.error("Cannot verify signature with message {}", e.getMessage());
        }
        return false;
    }

    private static PGPSignature extractSignature(byte[] signature) {
        try (
                ByteArrayInputStream signatureInput = new ByteArrayInputStream(signature);
                InputStream pgpInputStream = PGPUtil.getDecoderStream(signatureInput)
        ) {
            PGPObjectFactory pgpObjectFactory = new BcPGPObjectFactory(pgpInputStream);
            Object object = pgpObjectFactory.nextObject();

            if (!(object instanceof PGPSignatureList)) {
                log.error("Cannot extract signature since the object {} is not subclass of PGPSignatureList", object);
                return null;
            }

            PGPSignatureList signatureList = (PGPSignatureList) object;

            if (signatureList.isEmpty()) {
                log.error("Signature list generated from the signature byte does not contain any signature.");
                return null;
            }

            return signatureList.get(0);
        } catch (IOException e) {
            log.error("Cannot extract signature due to IOException with message: {}", e.getMessage());
        }
        return null;
    }

    private PGPSecretKey getSigningSecretKeyForId(String id) {
        try {
            Iterator<PGPSecretKeyRing> keyRingIterator = pgpSecretKeyRingCollection.getKeyRings(id);
            PGPSecretKey pgpSecretKey = null;
            while (keyRingIterator.hasNext()) {
                PGPSecretKeyRing secKeyRing = keyRingIterator.next();
                if (secKeyRing.getSecretKey().isSigningKey()) {
                    pgpSecretKey = secKeyRing.getSecretKey();
                }
            }

            if (pgpSecretKey == null) {
                log.warn("Secret signing key with id {} does not exists in the key rings!", id);
            }

            return pgpSecretKey;
        } catch (PGPException e) {
            log.error("Cannot get secret key from key rings with message: {}", e.getMessage());
        }
        return null;
    }

    private static String convertFingerprint(final byte[] fingerprintBytes) {
        final String indexLookup = "0123456789ABCDEF";
        final StringBuilder res = new StringBuilder();
        for (int index = fingerprintBytes.length - 1; index >= 0; index--) {
            int val = Byte.toUnsignedInt(fingerprintBytes[index]);
            int firstPos = val / 16;
            int secondPos =  val % 16;
            res.insert(0, indexLookup.charAt(secondPos));
            res.insert(0, indexLookup.charAt(firstPos));
        }
        for (int i = res.length() / 4 - 1; i > 0; i--) {
            res.insert(i * 4, " ");
        }
        return res.toString();
    }

    private static String convertUserIds(final Iterator<String> userIds) {
        final StringBuilder sb = new StringBuilder("[" + userIds.next());
        userIds.forEachRemaining(userId -> sb.append(String.format(", %s", userId)));
        return sb.append("]").toString();
    }
}
