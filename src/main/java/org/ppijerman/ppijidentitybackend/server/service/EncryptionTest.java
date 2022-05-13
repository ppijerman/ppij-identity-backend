package org.ppijerman.ppijidentitybackend.server.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
public class EncryptionTest {

    @Test
    void EncDec(){
       String encrypted = Encryption.Encrypt("testing");
       String decrypted = Encryption.Decrypt(encrypted);
       assertEquals(decrypted,"testing");
    }


}
