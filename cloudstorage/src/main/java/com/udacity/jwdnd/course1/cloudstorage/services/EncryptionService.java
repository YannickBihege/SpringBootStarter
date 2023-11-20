package com.udacity.jwdnd.course1.cloudstorage.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

@Service
public class EncryptionService {
    private Logger logger = LoggerFactory.getLogger(EncryptionService.class);

    private static final String SECRET_KEY_ALGORITHM = "PBKDF2WithHmacSHA256";
    private static final int ITERATION_COUNT = 65536; // Adjust as needed
    private static final int KEY_LENGTH = 128; // Key size in bits (128, 192, or 256)
    private static final String AES_ALGORITHM = "AES";

    public SecretKey generateKey(String password, String salt) {
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance(SECRET_KEY_ALGORITHM);
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), ITERATION_COUNT, KEY_LENGTH);
            SecretKey tmp = factory.generateSecret(spec);
            return new SecretKeySpec(tmp.getEncoded(), AES_ALGORITHM);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            logger.error("Error generating key: {}", e.getMessage());
            throw new RuntimeException("Error generating key", e);
        }
    }

    public String encryptValue(String data, String password, String salt) {
        try {
            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, generateKey(password, salt));
            byte[] encryptedBytes = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            logger.error("Error encrypting value: {}", e.getMessage());
            throw new RuntimeException("Error encrypting value", e);
        }
    }

    public String decryptValue(String data, String password, String salt) {
        try {
            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, generateKey(password, salt));
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(data));
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            logger.error("Error decrypting value: {}", e.getMessage());
            throw new RuntimeException("Error decrypting value", e);
        }
    }
}