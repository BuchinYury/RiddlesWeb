package io.buchin.common.hash;

import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by yuri on 14.03.17.
 */
public class HashPass {

    public static String passToDB(String password) {

        String sole = "bd0ff3aece06553e93eadedd15e321daa81d2d5985f1a6d7a645e1bfe18fe849";

        String hashPass = hashing(password) + sole;

        hashPass = mixWord(hashPass);

        hashPass = hashing(hashPass);

        return hashPass;
    }

    private static String hashing(String hashWord) {

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            return "";
        } finally {
            if (md == null) return "";
        }

        md.update(hashWord.getBytes());

        byte byteData[] = md.digest();

        //convert the byte to hex format
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }


    private static String mixWord(String wordToMix) {

        StringBuilder result = new StringBuilder();

        String[] subWord = new String[16];

        int subWordLength = subWord.length;

        char[] chars = wordToMix.toCharArray();

        for (int i = 0; i < subWordLength; i++) {
            StringBuilder split = new StringBuilder();

            for (int j = 0; j < 8; j++) {
                split.append(chars[8*i + j]);
            }

            if ((i & 1) == 0)  {
                split.reverse();
                subWord[i] = split.toString();
            } else subWord[subWordLength - i] = split.toString();

        }

        for (int i = 0; i < subWordLength; i++){
            result.append(subWord[i]);
        }

        return result.toString();
    }

}