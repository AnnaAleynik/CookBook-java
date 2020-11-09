package ru.itis.aleynik.cookingbook.services;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class PasswordCoder {

    private static final int PARAMETER3 = 65536;
    private static final int PARAMETER4 = 128;
    private static final String INSTANCE = "PBKDF2WithHmacSHA1";

    public static String getHash(String password, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), PARAMETER3, PARAMETER4);
        SecretKeyFactory factory = SecretKeyFactory.getInstance(INSTANCE);

        byte[] hash = factory.generateSecret(spec).getEncoded();
        String res = new String(hash, StandardCharsets.UTF_8);
        return res;
    }

    public static String getSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String res = new String(salt, StandardCharsets.UTF_8);
        return res;
    }

}
