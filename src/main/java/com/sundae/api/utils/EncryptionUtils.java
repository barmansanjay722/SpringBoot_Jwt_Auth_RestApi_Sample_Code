package com.sundae.api.utils;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * Utility class for generating password hashes using PBKDF2 (Password-Based Key Derivation Function 2) with HMAC SHA-1.
 */
public class EncryptionUtils {

    /**
     * Generates a hashed representation of the provided password using PBKDF2 with HMAC SHA-1.
     *
     * @param password The password to be hashed.
     * @param salt     The salt value used in the hashing process.
     * @return The generated password hash as a Base64-encoded string.
     * @throws NoSuchAlgorithmException If the specified algorithm is not available.
     * @throws InvalidKeySpecException  If the provided key specification is invalid.
     */
    public static String generateHash(String password, String salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = factory.generateSecret(spec).getEncoded();
        Base64.Encoder enc = Base64.getEncoder();
        return enc.encodeToString(hash);
    }
}
