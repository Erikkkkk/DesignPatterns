package nl.hanze.designpatterns.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

public class Executor {
	private final String databaseDriver;
	private final String databaseConnectionString;
	
	private Connection connect;
	
	private static Executor executor;
	
	private Executor() {
		databaseDriver = "com.mysql.jdbc.Driver";
		
		String databaseURL = "jdbc:mysql:localhost";
		String databaseName = "funda2";
		String databaseUser = "root";
		String databasePassword = "";
		
		databaseConnectionString = databaseDriver + "//" + databaseURL + "/" + databaseName + "?user=" +
				databaseUser + "&password=" + databasePassword;
	}
	
	public Executor getExecutor() {
		
		if(executor == null) {
			executor = new Executor();
			connectToMySQL();
		}
		
		return executor;
		
	}
	
	private void connectToMySQL() {
		try {
			Class.forName(databaseDriver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			connect = DriverManager.getConnection(databaseConnectionString);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void executeQuery(String query) throws Exception {
	}
	
	public <T> Iterator<T> getIterator(String query, Class<T> c) throws Exception {
		return null;
	}
}
