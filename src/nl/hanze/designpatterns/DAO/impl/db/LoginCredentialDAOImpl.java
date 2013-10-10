package nl.hanze.designpatterns.DAO.impl.db;

import java.util.Iterator;
import nl.hanze.designpatterns.db.RowIterator;

import nl.hanze.designpatterns.domain.LoginCredential;

public class LoginCredentialDAOImpl extends BaseDAOImpl {
	
	
	public LoginCredentialDAOImpl() {
		
	}
	
	public boolean isValid(LoginCredential logincredential) throws Exception {
		String query = "SELECT * FROM logincredential";
		Iterator<LoginCredential> rowIterator = executor.getIterator(query, LoginCredential.class);
		
		while(rowIterator.hasNext()){
                    LoginCredential next = rowIterator.next();
                    System.out.println(next.getUserName());
                    System.out.println(next.getPassword());
                    if(next.getUserName().equalsIgnoreCase(logincredential.getUserName())
                         && next.getPassword().equals(logincredential.getPassword())){
                        System.out.println("goed");
                        return true;
                    }
                }
		return false;
	}

}
