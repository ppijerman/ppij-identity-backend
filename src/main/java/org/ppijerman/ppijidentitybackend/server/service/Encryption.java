package org.ppijerman.ppijidentitybackend.server.service;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("enc")
public class Encryption {
    public static String Encrypt(String dec){
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setPoolSize(4);
        encryptor.setPassword(System.getenv("MASTER_KEY"));
        encryptor.setAlgorithm("PBEWithMD5AndTripleDES");
        String encryptedText = encryptor.encrypt(dec);
        return encryptedText;
    }

    public static String Decrypt(String enc){
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setPoolSize(4);
        encryptor.setPassword(System.getenv("MASTER_KEY"));
        encryptor.setAlgorithm("PBEWithMD5AndTripleDES");
        String decryptedText = encryptor.decrypt(enc);
        return decryptedText;
    }

}