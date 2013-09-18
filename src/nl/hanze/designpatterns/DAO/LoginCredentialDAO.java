package nl.hanze.designpatterns.DAO;

import nl.hanze.designpatterns.domain.*;

public interface LoginCredentialDAO {
	public boolean isValid(LoginCredential logincredential);
}
