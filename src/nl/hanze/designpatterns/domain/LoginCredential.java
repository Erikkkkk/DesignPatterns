package nl.hanze.designpatterns.domain;

public class LoginCredential {
	private String userName;
	private String password;
	
	public LoginCredential() {}
	
	public LoginCredential(String userName, String password) {
		this.setUserName(userName);
		this.setPassword(password);
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return userName+"::"+password;
	}
}
