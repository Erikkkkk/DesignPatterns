package nl.hanze.designpatterns.DAO.impl.file.crypted;

import java.io.File;
import java.io.IOException;
import nl.hanze.designpatterns.DAO.impl.db.*;
import java.util.Iterator;
import nl.hanze.designpatterns.DAO.impl.file.ser.CryptedInputStream;
import nl.hanze.designpatterns.DAO.impl.file.ser.CryptedOutputStream;
import nl.hanze.designpatterns.db.RowIterator;

import nl.hanze.designpatterns.domain.LoginCredential;

public class LoginCredentialDAOFileImpl extends BaseDAOImpl {

    protected CryptedInputStream cryptedInputStream;
    protected CryptedOutputStream cryptedOutputStream;

    public LoginCredentialDAOFileImpl() throws IOException, Throwable {
        File loginFile = new File("login.txt");
        if (!loginFile.exists()) {
            loginFile.createNewFile();
        }
        this.cryptedInputStream = new CryptedInputStream(loginFile);
        this.cryptedOutputStream = new CryptedOutputStream(loginFile);
        this.cryptedOutputStream.writeEncrypted("design:patterns");
    }

    public boolean isValid(LoginCredential logincredential) throws Exception {

        String str = this.cryptedInputStream.readAndDecrypt("ThisIsASecretKey");
        System.out.println("the string in file"+str);
        
        if(str.equalsIgnoreCase(logincredential.getUserName()+":"+logincredential.getPassword())){
                    System.out.println("goed");

            return true;
        }
        
        return false;


    }
}
