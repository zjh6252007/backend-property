package com.jay.pmanage.util;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class encryptUtil {
    public static String generateSalt(){
        return Long.toString(System.nanoTime());
    }

    public static String encodePassword(String password,String salt){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest((password+salt).getBytes());
            return Base64.getEncoder().encodeToString(hash);
        }catch (NoSuchAlgorithmException e){
            throw new RuntimeException("Not found",e);
        }
    }
}
