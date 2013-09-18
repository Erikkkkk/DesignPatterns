package nl.hanze.designpatterns.DAO;

import nl.hanze.designpatterns.domain.*;
import java.util.*;

public interface TroubleTicketDAO {
	public List<TroubleTicket> getTroubleTicketsByName(String callerName);
	public void saveTroubleTicket(TroubleTicket troubleticket);
}
