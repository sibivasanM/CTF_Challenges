package com.example.securewebapp.crypto;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Secure {

    private static final int GCM_TAG_LENGTH = 16;

    public static byte[] encryptStr(String str) {
        byte[] decodedKey = Base64.getDecoder().decode("MTIzNDU2Nzg5MDEyMzQ1Njc4OTAxMjM0NTY3ODkwMTI="); // 32-byte key
        SecretKey key = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
        byte[] IV = {0x00};
        try {
            return encrypt(str.getBytes(), key, IV);
        } catch (Exception e) {
            throw new RuntimeException("Encryption failed", e);
        }
    }

    public static byte[] encrypt(byte[] plaintext, SecretKey key, byte[] IV) throws Exception {
        Cipher cip = Cipher.getInstance("AES/GCM/NoPadding");
        SecretKeySpec spec = new SecretKeySpec(key.getEncoded(), "AES");
        GCMParameterSpec paramSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, IV);
        cip.init(Cipher.ENCRYPT_MODE, spec, paramSpec);
        return cip.doFinal(plaintext);
    }
}

