/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hanze.designpatterns.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import nl.hanze.designpatterns.DAO.impl.db.TroubleTicketDAOImpl;
import nl.hanze.designpatterns.domain.TroubleTicket;
import nl.hanze.designpatterns.view.MyTicketsView;

/**
 *
 * @author glenndeb
 */
public class MyTicketsController  {
    
    public MyTicketsView view;
    private ActionListener onSelectClick;

    public MyTicketsController(ContainerController parent) {
        this.view = new MyTicketsView();
        parent.addTabbar("Trouble tickets",this.view);
        
        TroubleTicketDAOImpl dao = new TroubleTicketDAOImpl();
        try {
        	updateTable(dao.getTroubleTicketsByName(""));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        onSelectClick = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                String caller = view.getUsernameCallerText().getText();               
                System.out.println("To select tickets of user: " + caller);
                
                TroubleTicketDAOImpl dao = new TroubleTicketDAOImpl();
                try {
                	updateTable(dao.getTroubleTicketsByName(caller));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                   
            }
        };
        view.getSelectButton().addActionListener(onSelectClick);
    }
    
    private void updateTable(List<TroubleTicket> tickets) {
    	for(int i = 0; i < 20; i++) {
    		view.updateTableValue("", i, 0);
    		view.updateTableValue("", i, 1);
    		view.updateTableValue("", i, 2);
    		view.updateTableValue("", i, 3);
    	}
    	
    	for(int i = 0; i < tickets.size(); i++) {
    		view.updateTableValue(tickets.get(i).getCallerName(), i, 0);
    		view.updateTableValue(tickets.get(i).getDateTime().toString(), i, 1);
    		view.updateTableValue(tickets.get(i).getDescription(), i, 2);
    		view.updateTableValue(tickets.get(i).getUserName(), i, 3);
    	}
    }
    
}
