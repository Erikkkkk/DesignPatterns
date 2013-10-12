/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hanze.designpatterns.DAO.impl.file.ser;

import java.io.*;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
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
public class CryptedInputStream extends FileInputStream {

    public CryptedInputStream(File file) throws FileNotFoundException {
        super(file);
    }

    public String readAndDecrypt(String key) throws GeneralSecurityException, IOException {
        int content;
        BufferedReader br;
        br = new BufferedReader(new InputStreamReader((FileInputStream)this));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
            sb.append('\n');
        }
        String encrypted = sb.toString();
        try {
            byte[] raw;

            raw = key.getBytes();
            if (raw.length != 16) {
                throw new IllegalArgumentException("Invalid key size.");
            }


            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec,
                    new IvParameterSpec(new byte[16]));
            byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted.getBytes()));
            br.close();
            return new String(original);
        } catch (Exception ex) {
            Logger.getLogger(CryptedInputStream.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
