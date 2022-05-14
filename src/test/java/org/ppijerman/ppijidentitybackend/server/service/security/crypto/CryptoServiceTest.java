package org.ppijerman.ppijidentitybackend.server.service.security.crypto;

import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPSignature;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CryptoServiceTest {
    private static CryptoService cryptoService;
    private static final String PRIVATE_KEY = "privateKey";
    private static final int HASH_ROUND = 10;
    private static final String signerId = "No Reply PPI Jerman <no-reply-test@ppijerman.org>";
    private static final String PATH_TO_SECRET_KEY = "security/test-pgp.asc";
    private static final String PGP_KEY_PASSPHRASE = "testPassword";
    private static final String HASH_ALGORITHM = "SHA256";

    @BeforeAll
    public static void setUp() throws PGPException, IOException, NoSuchFieldException, IllegalAccessException {
        cryptoService = new CryptoService(
                PRIVATE_KEY,
                HASH_ROUND,
                PATH_TO_SECRET_KEY,
                PGP_KEY_PASSPHRASE,
                HASH_ALGORITHM
        );
    }

    @Test
    public void testBasicHashAndVerify() {
        final String plainText = "TEST";
        assertTrue(cryptoService.checkHash(plainText, cryptoService.hash(plainText)));
    }

    @Test
    public void testPasswordEncoderHashing() {
        final String plainText = "Text";
        assertTrue(cryptoService.matches(plainText, cryptoService.hash(plainText)));
    }

    @Test
    public void testEncryptAndDecryptSerializable() {
        String plainText = "This is serializable";
        assertEquals(plainText, cryptoService.decrypt(cryptoService.encrypt(plainText)));
    }

    @Test
    public void testEncryptAndDecryptUUID() {
        UUID uuid = UUID.randomUUID();
        assertEquals(uuid, cryptoService.decryptToUUID(cryptoService.encryptUUID(uuid)));
    }

    @Test
    public void testEncryptAndDecryptCalendar() {
        Calendar calendar = Calendar.getInstance();
        assertEquals(calendar, cryptoService.decryptToCalendar(cryptoService.encryptCalendar(calendar)));
    }

    @Test
    public void testEncryptAndDecryptInt() {
        int val = 69;
        assertEquals(val, cryptoService.decryptToInt(cryptoService.encryptInt(val)));
    }

    @Test
    public void testEncryptAndDecryptDate() {
        Date date = Date.from(Instant.now());
        assertEquals(date, cryptoService.decryptToDate(cryptoService.encryptDate(date)));
    }

    @Test
    public void testSignAndVerifyData() {
        byte[] plainBytes = new byte[20];
        new Random().nextBytes(plainBytes);
        byte[] signature = cryptoService.sign(PGPSignature.CANONICAL_TEXT_DOCUMENT, plainBytes, signerId);
        assertTrue(cryptoService.verify(signature, plainBytes, signerId));
    }
}
