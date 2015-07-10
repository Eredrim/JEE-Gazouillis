package main.java.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by axel on 10/07/2015.
 */
public class Encrypt {
    public static String encrypt(String toEncrypt) {
        String generated = "";

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            md.update(toEncrypt.getBytes());

            byte[] bytes = md.digest();

            StringBuilder sb = new StringBuilder();

            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            generated = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generated;
    }
}
