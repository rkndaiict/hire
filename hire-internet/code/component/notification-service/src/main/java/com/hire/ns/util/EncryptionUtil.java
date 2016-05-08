/*
 * Copyright © 2008 ScriptRx, Inc. The information in this document is subject to change without notice and does not
 * represent a commitment by ScriptRx, Inc. The software and/or databases described in this document are furnished under
 * a license agreement and may be used or copied only in accordance with the terms of the agreement. Without limiting
 * the rights under copyright reserved below, and except as permitted by such license, no part of this document and/or
 * database may be reproduced or transmitted in any form or by any means, including, without limitation, electronic,
 * mechanical, photocopying, recording, or otherwise, or transferred to information storage and retrieval systems,
 * without the prior written permission of ScriptRx, Inc. Restricted Rights: Use, duplication, or disclosure by the
 * United States Government is subject to restrictions as set forth in subparagraph (c)(1)(ii) of the Rights in
 * Technical Data and Computer Software clause at DFARS 252.227-7013 or subparagraphs (c)(1) and (2) of the Commercial
 * Computer Software – Restricted Rights at 48 CFR 52.227-19, as applicable. Copyright © 2008 ScriptRx, Inc. All rights
 * reserved. $Author: Vipin Gupta $
 */
package com.hire.ns.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * <code>EncryptionUtil</code> is a class which is used to do the encryption and decryption of ScriptRx secure values
 * into the database.
 */
public class EncryptionUtil {

    /** Encryption scheme. */
    private static final String DES_ENCRYPTION_SCHEME = "DES";

    /** Key used during encryption and decryption. */
    private static final String STANDARD_KEY = "This is a scriptrx long phrase used to encrypt";

    /** The string format while encryption and decryption. */
    private static final String UNICODE_FORMAT = "UTF8";

    /**
     * Returns the encrypted value of the passed unencrypted string based on DES scheme.
     * 
     * @param pUnencryptedString is the value to be encrypted.
     * @return encrypted value of the passed unencrypted string
     */
    public static synchronized String encryptString(String pUnencryptedString) {

        if (pUnencryptedString == null) {
            return null;
        }
        KeySpec keySpec = null;
        SecretKeyFactory keyFactory = null;
        Cipher cipher = null;
        try {

            /**
             * Step 1. Generate an DES key using KeyGenerator
             */
            byte[] keyBytes = STANDARD_KEY.getBytes(UNICODE_FORMAT);
            keyFactory = SecretKeyFactory.getInstance(DES_ENCRYPTION_SCHEME);
            keySpec = new DESKeySpec(keyBytes);
            /**
             * Step2. Create a Cipher by specifying the following parameters a. Algorithm name - here it is DES
             */

            cipher = Cipher.getInstance(DES_ENCRYPTION_SCHEME);
            SecretKey secretkey = keyFactory.generateSecret(keySpec);
            cipher.init(Cipher.ENCRYPT_MODE, secretkey);
            /**
             * Step 4. Encrypt the Data 1. Declare / Initialize the Data. Here the data is of type String 2. Convert the
             * Input Text to Bytes 3. Encrypt the bytes using doFinal method
             */

            byte[] byteDataToEncrypt = pUnencryptedString.getBytes(UNICODE_FORMAT);
            byte[] byteCipherText = cipher.doFinal(byteDataToEncrypt);
            return new Base64().encodeToString(byteCipherText);

        } catch (BadPaddingException badPadding) {
            return null;
        } catch (IllegalBlockSizeException illegalBlockSize) {
            return null;
        } catch (InvalidKeySpecException e) {
            return null;
        } catch (InvalidKeyException e) {
            return null;
        } catch (UnsupportedEncodingException e) {
            return null;
        } catch (NoSuchAlgorithmException e) {
            return null;
        } catch (NoSuchPaddingException e) {
            return null;
        }
    }

    /**
     * Returns the decrypted value of the passed encrypted string based on DES scheme.
     * 
     * @param pEncryptedString is the value to be decrypted.
     * @return decrypted value of the passed encrypted string
     */
    public static synchronized String decryptString(String pEncryptedString) {

        if (pEncryptedString == null) {
            return null;
        }

        KeySpec keySpec = null;
        SecretKeyFactory keyFactory = null;
        Cipher cipher = null;
        try {
            /**
             * Step 1. Generate an DES key using KeyGenerator
             */
            byte[] keyBytes = STANDARD_KEY.getBytes(UNICODE_FORMAT);
            keyFactory = SecretKeyFactory.getInstance(DES_ENCRYPTION_SCHEME);
            keySpec = new DESKeySpec(keyBytes);
            /**
             * Step2. Create a Cipher by specifying the following parameters a. Algorithm name - here it is DES
             */
            cipher = Cipher.getInstance(DES_ENCRYPTION_SCHEME);

            SecretKey secretkey = keyFactory.generateSecret(keySpec);
            cipher.init(Cipher.DECRYPT_MODE, secretkey);
            Base64 base64decoder = new Base64();
            byte[] cleartext = base64decoder.decodeBase64(pEncryptedString);
            byte[] ciphertext = cipher.doFinal(cleartext);

            return bytes2String(ciphertext);
        } catch (InvalidKeyException invalidKey) {
            return null;
        }

        catch (BadPaddingException badPadding) {
            return null;
        } catch (IllegalBlockSizeException illegalBlockSize) {
            return null;
        } catch (InvalidKeySpecException e) {
            return null;
        } catch (IOException e) {
            return null;
        } catch (NoSuchAlgorithmException e) {
            return null;
        } catch (NoSuchPaddingException e) {
            return null;
        }
    }

    /**
     * Convert the byte array to string.
     * 
     * @param bytes to be converted to string
     * @return string of the passed bytes
     */
    private static String bytes2String(byte[] bytes) {

        if (bytes == null) {
            return null;
        }

        try {
            return new String(bytes, 0, bytes.length, UNICODE_FORMAT);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * The main method.
     * 
     * @param args the arguments
     */
    public static void main(String[] args) {
    }
}
