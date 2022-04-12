package org.ppijerman.ppijidentitybackend.server.service.security.crypto;

import org.bouncycastle.bcpg.HashAlgorithmTags;
import org.bouncycastle.openpgp.*;
import org.bouncycastle.openpgp.operator.PBESecretKeyDecryptor;
import org.bouncycastle.openpgp.operator.bc.BcKeyFingerprintCalculator;
import org.bouncycastle.openpgp.operator.bc.BcPBESecretKeyDecryptorBuilder;
import org.bouncycastle.openpgp.operator.bc.BcPGPContentSignerBuilder;
import org.bouncycastle.openpgp.operator.bc.BcPGPDigestCalculatorProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.UUID;

@Component
public class CryptoService implements PasswordEncoder {

    private final Logger log = LoggerFactory.getLogger(CryptoService.class);

    private final BytesEncryptor bytesEncryptor;
    private final int HASH_ROUNDS;
    private final PGPSecretKeyRingCollection pgpSecretKeyRingCollection;
    private final PGPSignatureGenerator pgpSignatureGenerator;
    private final String pgpKeyPassphrase;
    private final long pgpKeyId;

    public CryptoService(
        @Value("${ppij-id.security.encryption.private-key}") String privateKey,
        @Value("${ppij-id.security.encryption.hash-rounds:10}") int hashRounds,
        @Value("${ppij-id.security.encryption.pgp.secret-key}") String pathToSecretKey,
        @Value("${ppij-id.security.encryption.pgp.key-id}") long pgpKeyId,
        @Value("${ppij-id.security.encryption.pgp.key-passphrase:#{null}}") String pgpKeyPassphrase,
        @Value("${ppij-id.security.encryption.pgp.hash-algorithm:SHA256}") String hashAlgorithm
    ) throws IOException, PGPException, NoSuchFieldException, IllegalAccessException {
        if (hashRounds > 30) {
            log.warn("Number of iteration is considered too big ({}/{}).", hashRounds, 30);
        }
        this.bytesEncryptor = Encryptors.stronger(privateKey, KeyGenerators.string().generateKey());
        this.HASH_ROUNDS = hashRounds;
        this.pgpKeyId = pgpKeyId;
        this.pgpKeyPassphrase = pgpKeyPassphrase;

        try (
                InputStream secretKeyInputStream = new FileInputStream(pathToSecretKey)
        ) {
            this.pgpSecretKeyRingCollection = new PGPSecretKeyRingCollection(secretKeyInputStream, new BcKeyFingerprintCalculator());

            pgpSecretKeyRingCollection.getKeyRings().forEachRemaining(pgpPublicKeys -> {
                log.info("Loaded secret key with fingerprint: {}", pgpPublicKeys.getPublicKey().getFingerprint());
            });

            final PGPSecretKey pgpSecretKey = pgpSecretKeyRingCollection.getSecretKey(pgpKeyId);
            if (pgpSecretKey == null) {
                throw new RuntimeException(String.format("Cannot find secret key with given key id %d (%X)!", pgpKeyId, pgpKeyId));
            } else if (!pgpSecretKey.isSigningKey()) {
                throw new RuntimeException(String.format("Secret key with given key id %d (%X) is not a signing key!", pgpKeyId, pgpKeyId));
            }

            int hashAlgorithmInt = HashAlgorithmTags.class.getField(hashAlgorithm).getInt(HashAlgorithmTags.class);
            this.pgpSignatureGenerator = new PGPSignatureGenerator(new BcPGPContentSignerBuilder(pgpSecretKey.getPublicKey().getAlgorithm(), hashAlgorithmInt));
        }
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return this.hash(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return this.encode(rawPassword).equals(encodedPassword);
    }

    public String hash(String plainText) {
        return BCrypt.hashpw(plainText, BCrypt.gensalt(HASH_ROUNDS));
    }

    public boolean checkHash(String plainText, String hashed) {
        return BCrypt.checkpw(plainText, hashed);
    }

    public byte[] encrypt(Serializable serializable) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
            objectOutputStream.writeObject(serializable);
            objectOutputStream.flush();
            byte[] readBytes = byteArrayOutputStream.toByteArray();
            return this.encryptBytes(readBytes);
        } catch (IOException e) {
            log.error("IOException happen when encrypting serializable object with message: {}", e.getMessage());
            return null;
        }
    }

    public byte[] encryptBytes(byte[] plainBytes) {
        return bytesEncryptor.encrypt(plainBytes);
    }

    public byte[] encryptString(String plainText) {
        return this.encryptBytes(plainText.getBytes(StandardCharsets.UTF_8));
    }

    public byte[] encryptInt(int plainInt) {
        return this.encryptString(plainInt + "");
    }

    public byte[] encryptUUID(UUID plainUuid) {
        return this.encryptString(plainUuid.toString());
    }

    public byte[] encryptCalendar(Calendar calendar) {
        return this.encryptString(calendar.toInstant().toString());
    }

    public byte[] decryptToBytes(byte[] cipherBytes) {
        return bytesEncryptor.decrypt(cipherBytes);
    }

    public String decryptToString(byte[] cipherBytes) {
        return new String(this.decryptToBytes(cipherBytes));
    }

    public int decryptToInt(byte[] cipherBytes) {
        return Integer.parseInt(this.decryptToString(cipherBytes));
    }

    public UUID decryptToUUID(byte[] cipherBytes) {
        return UUID.fromString(this.decryptToString(cipherBytes));
    }

    public Calendar decryptToCalendar(byte[] cipherBytes) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Instant.parse(this.decryptToString(cipherBytes)).toEpochMilli());
        return calendar;
    }

    public byte[] sign(int signatureType, byte[] plainBytes) {
        try {
            final PGPSecretKey pgpSecretKey = pgpSecretKeyRingCollection.getSecretKey(pgpKeyId);

            if (pgpSecretKey == null) {
                log.error(String.format("Secret key with id %d (%X) is not a signing key!", pgpKeyId, pgpKeyId));
                return null;
            }

            if (!pgpSecretKey.isSigningKey()) {
                log.error(String.format("Secret key with id %d (%X) is not a signing key!", pgpKeyId, pgpKeyId));
                return null;
            }

            final PBESecretKeyDecryptor secretKeyDecryptor = new BcPBESecretKeyDecryptorBuilder(new BcPGPDigestCalculatorProvider()).build(pgpKeyPassphrase.toCharArray());
            final PGPPrivateKey pgpPrivateKey = pgpSecretKey.extractPrivateKey(secretKeyDecryptor);
            pgpSignatureGenerator.init(signatureType, pgpPrivateKey);
            pgpSignatureGenerator.update(plainBytes);

            return pgpSignatureGenerator.generate().getEncoded(false);
        } catch (PGPException e) {
            log.error("Got PGPException when signing with message: {}", e.getMessage());
        } catch (IOException e) {
            log.error("Got IOException when signing with message: {}", e.getMessage());
        }
        return null;
    }
}
