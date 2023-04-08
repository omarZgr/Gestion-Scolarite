package com.example.start.CryptoInfo;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class MyAESApp {
    private static SecretKeySpec secretKey ;
   // final String secretKey11= "donotspeakAboutThis" ;

    private static byte[] key ;

    public static void setKey(String myKey)
    {
      try {
          key = myKey.getBytes("UTF-8") ;
          MessageDigest sha = MessageDigest.getInstance("SHA-1") ;
          key = sha.digest(key) ;
          key = Arrays.copyOf(key,16) ;
          secretKey = new SecretKeySpec(key,"AES") ;

      } catch (UnsupportedEncodingException e) {
          throw new RuntimeException(e);
      } catch (NoSuchAlgorithmException e) {
          throw new RuntimeException(e);
      }
    }

    public static String encrypt(String strToEnc,String sec)
    {
        try {
            setKey(sec);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding") ;
            cipher.init(Cipher.ENCRYPT_MODE,secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEnc.getBytes("UTF-8"))) ;

        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        }

    }

    public static String decrypt(String strToDec,String sec) {
        try {
            setKey(sec);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDec)));

        } catch (Exception e) {
        }

        return null ;

    }

    public static void main(String[] args) {

       final String secretKey = "donotspeakAboutThis" ;
       String originalString = "omar@0662459322" ;

       String encSite = MyAESApp.encrypt(originalString,secretKey) ;
       String decSite = MyAESApp.decrypt(encSite,secretKey) ;


        System.out.println("Original :  " + originalString);
        System.out.println("Encrypted text :  " + encSite);
        System.out.println("Decrypted text :  " + decSite);
    }


}
