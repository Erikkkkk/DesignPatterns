package nl.hanze.designpatterns.DAO.impl.db;

import java.util.Iterator;
import nl.hanze.designpatterns.db.RowIterator;

import nl.hanze.designpatterns.domain.LoginCredential;

public class LoginCredentialDAOImpl extends BaseDAOImpl {
	
	
	public LoginCredentialDAOImpl() {
		
	}
	
	
	public boolean isValid(LoginCredential logincredential) throws Exception {
		String query = "select * from logincredential";
		RowIterator rowIterator = (RowIterator) executor.getIterator(query, LoginCredential.class);
		
		while(rowIterator.hasNext()){
                    LoginCredential next = (LoginCredential)rowIterator.next();
                    System.out.println(logincredential.getUserName());
                    System.out.println(logincredential.getPassword());
                    if(next.getUserName().equalsIgnoreCase(logincredential.getUserName())
                         && next.getPassword().equals(logincredential.getPassword())){
                        System.out.println("goed");
                        return true;
                    }
                    
                }
		return false;
	}

}
