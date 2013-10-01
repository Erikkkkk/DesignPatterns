package nl.hanze.designpatterns.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Executor {
	private final String databaseDriver;
	private final String databaseConnectionString;
	
	private Connection connect;
	
	private static Executor executor;
	
	private Executor() {
		databaseDriver = "com.mysql.jdbc.Driver";
		
		String databaseURL = "jdbc:mysql:localhost";
		String databaseName = "designpatterns";
		String databaseUser = "root";
		String databasePassword = "";
		
		databaseConnectionString = databaseDriver + "//" + databaseURL + "/" + databaseName + "?user=" +
				databaseUser + "&password=" + databasePassword;
		
		connectToMySQL();
	}
	
	public static Executor getExecutor() {
		
		if(executor == null) {
			executor = new Executor();
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
		connect.prepareStatement(query);
	}
	
	public <T> Iterator<T> getIterator(String query, Class<T> c) throws Exception {
		Statement statement = connect.createStatement(); 
	    ResultSet res = statement.executeQuery(query);
	    
	    RowIterator rowIterator = new RowIterator<T>(c, res);
	    
	    
		
		return null;
	}
}
