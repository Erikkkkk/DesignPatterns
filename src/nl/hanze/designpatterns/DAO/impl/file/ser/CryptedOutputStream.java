/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hanze.designpatterns.DAO.impl.file.ser;

import java.io.*;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author glenndeb
 */
public class CryptedOutputStream extends FileOutputStream {

    public CryptedOutputStream(File file) throws FileNotFoundException {
        super(file);
    }

    public void writeEncrypted(String string) throws Throwable {
        String secret = "ThisIsASecretKey";
        byte[] raw = secret.getBytes();
        if (raw.length != 16) {
            throw new IllegalArgumentException("Invalid key size.");
        }

        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec,
                new IvParameterSpec(new byte[16]));
        this.write( Base64.encodeBase64String(cipher.doFinal(string.getBytes())).getBytes());
        this.close();
    }
    
    
}
