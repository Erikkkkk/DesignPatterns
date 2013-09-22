package nl.hanze.designpatterns.DAO.impl.db;

import nl.hanze.designpatterns.db.Executor;
import nl.hanze.designpatterns.domain.LoginCredential;

public class LoginCredentialDAOImpl {
	Executor executor;
	
	public LoginCredentialDAOImpl() {
		executor = Executor.getExecutor();
	}
	
	
	public boolean isValid(LoginCredential logincredential) throws Exception {
		String query = "select count(*) from user where ";
		executor.executeQuery(query);
		return false;
	}

}
