package nl.hanze.designpatterns.domain;

public class TroubleTicket {
	private String userName;
	private String callerName;
	private String description;
	private String dateTime;
	
	public TroubleTicket(String userName, String callerName, String description, String dateTime) {
		this.setUserName(userName);
		this.setCallerName(callerName);
		this.setDescription(description);
		this.setDateTime(dateTime);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCallerName() {
		return callerName;
	}

	public void setCallerName(String callerName) {
		this.callerName = callerName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
}
