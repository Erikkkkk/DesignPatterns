package nl.hanze.designpatterns.DAO.impl.db;

import java.util.ArrayList;
import java.util.List;

import nl.hanze.designpatterns.db.RowIterator;
import nl.hanze.designpatterns.domain.LoginCredential;
import nl.hanze.designpatterns.domain.TroubleTicket;

public class TroubleTicketDAOImpl extends BaseDAOImpl {
		
	public TroubleTicketDAOImpl() {
		
	}

	public boolean saveTroubleTicket(TroubleTicket troubleticket) {
		boolean succes = true;
		String query = "INSERT INTO  `designpatterns`.`troubleticket` (`id`, `description`, "
				+ "`callerName`, `userName`, `dateTime`) VALUES (NULL, '" + troubleticket.getDescription() + 
				"', '" + troubleticket.getCallerName() + "', "
				+ "'" + troubleticket.getUserName() + "', '" + troubleticket.getDateTime() + "');";
		try {
			executor.executeQuery(query);
			System.out.println("Trouble ticket insert query executed!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			succes = false;
		}
		return succes;
	}
	
	public List<TroubleTicket> getTroubleTicketsByName(String callerName) throws Exception {
		List<TroubleTicket> tickets = new ArrayList<TroubleTicket>();
		String query = "";
		if(callerName.length() > 0) {
			query = "select * from troubleticket where `callerName` = '" + callerName + "';";
		} else {
			query = "select * from troubleticket";
		}
		
		RowIterator rowIterator = (RowIterator) executor.getIterator(query, TroubleTicket.class);
		
		while(rowIterator.hasNext()){
			TroubleTicket next = (TroubleTicket) rowIterator.next();
			//System.out.println("Row - troubleticket caller: "
			//		+ next.getCallerName() + " description: " + next.getDescription());

			tickets.add(next);
		}
		
		return tickets;
	}
	
}
