package nl.hanze.designpatterns.DAO.impl.db;

import nl.hanze.designpatterns.db.Executor;

public class BaseDAOImpl {
	Executor executor;
	
	public BaseDAOImpl() {
		executor = Executor.getExecutor();
	}
	

}
