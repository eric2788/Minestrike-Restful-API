package com.ericlam.minestrike.utils;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class Utils {

    public static Map returnResult(boolean result) {
        Map<String, Boolean> resultMap = new HashMap<>();
        resultMap.put("success", result);
        return resultMap;
    }

    public static Map returnCustomError(String errorMSG) {
        Map<String, Object> error = new HashMap<>();
        error.put("success", false);
        error.put("error", errorMSG);
        return error;
    }

    public static String toHash64(String password) {
        try {
            byte[] pwBytes = password.getBytes(StandardCharsets.UTF_8);
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(pwBytes);
            byte[] base64 = Base64.getEncoder().encode(hash);
            StringBuilder builder = new StringBuilder();
            for (byte b : base64) {
                builder.append((char) b);
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return password;
        }
    }
}
