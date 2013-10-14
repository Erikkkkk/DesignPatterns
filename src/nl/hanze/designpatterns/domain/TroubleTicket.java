package nl.hanze.designpatterns.domain;

import java.sql.Timestamp;

public class TroubleTicket {
	private Integer id;
	private Timestamp timestamp;
	private String userName;
	private String callerName;
	private String description;
	private Timestamp dateTime;
	
	public TroubleTicket() {}
	
	public TroubleTicket(Integer id, Timestamp timestamp ,String userName, String callerName, String description, Timestamp dateTime) {
		this.setId(id);
		this.setTimestamp(timestamp);
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
	
	public void setId(Integer id) {
		this.id = id;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	public Integer getId() {
		return id;
	}
	
	public Timestamp getTimestamp() {
		return timestamp;
	}

	public Timestamp getDateTime() {
		return dateTime;
	}

	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}
}
