package org.ppijerman.ppijidentitybackend.server.service.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Calendar;
import java.util.UUID;

@Component
public class EncryptionService {

    private final Logger log = LoggerFactory.getLogger(EncryptionService.class);

    private final BytesEncryptor bytesEncryptor;
    private final int HASH_ROUNDS;

    public EncryptionService (
        @Value("${ppij-id.security.encryption.private-key}") String privateKey,
        @Value("${ppij-id.security.encryption.hash-rounds:10}") int hashRounds
    ) {
        if (hashRounds > 30) {
            log.warn("Number of iteration is considered too big ({}/{}).", hashRounds, 30);
        }
        this.bytesEncryptor = Encryptors.stronger(privateKey, KeyGenerators.string().generateKey());
        this.HASH_ROUNDS = hashRounds;
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
}
