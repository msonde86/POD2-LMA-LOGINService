package com.scb.pod2.login.util;



import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

@Component
public class PasswordDataEncryption {

    private static SecretKeySpec secretKey;
    private static byte[] key;
    private static final String ALGORITHM = "AES";
    
    /**
     * Prepare Secret Key by specifying "AES" ALGORITHM
     * @param myKey - Secret key need to be passed
     */
	
    public void prepareSecreteKey(String myKey) {
        MessageDigest sha = null;
        try {
            	key = myKey.getBytes(StandardCharsets.UTF_8);
            	sha = MessageDigest.getInstance("SHA-1");
            	key = sha.digest(key);
            	key = Arrays.copyOf(key, 16);
            	secretKey = new SecretKeySpec(key, ALGORITHM);
            } catch (NoSuchAlgorithmException e) {
            	e.printStackTrace(); //Use proper logging in actual implementation, dont keep this stacktrace
            }
    }

    /**
     * Encrypt password string based on secret Key before saving it in database
     * @param strToEncrypt - Pass password string
     * @param secret - it should be always "secrete"
     */

    public String encryptPassword(String strToEncrypt, String secret) {
        try {
            prepareSecreteKey(secret);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString()); //Use proper logging in actual implementation, dont keep this Sysout
        }
        return null;
    }

     /**
     * Decrypt password string based on secret Key after fetching from database
     * @param strToEncrypt - Pass password string
     * @param secret - it should be always "secrete"
     */

    public String decryptPassword(String strToDecrypt, String secret) {
        try {
            prepareSecreteKey(secret);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString()); //Use proper logging in actual implementation, dont keep this Sysout
        }
        return null;
    }

}