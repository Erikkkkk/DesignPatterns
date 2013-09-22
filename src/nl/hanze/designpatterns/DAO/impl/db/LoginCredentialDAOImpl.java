package nl.hanze.designpatterns.DAO.impl.db;

import java.util.Iterator;

import nl.hanze.designpatterns.domain.LoginCredential;

public class LoginCredentialDAOImpl extends BaseDAOImpl {
	
	
	public LoginCredentialDAOImpl() {
		
	}
	
	
	public boolean isValid(LoginCredential logincredential) throws Exception {
		String query = "select * from logincredential where userName=" + logincredential.getUserName() + 
				" and password=" + logincredential.getPassword();
		executor.getIterator(query, LoginCredential.class);
		return false;
	}

}
