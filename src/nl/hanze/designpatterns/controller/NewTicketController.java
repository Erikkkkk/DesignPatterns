/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hanze.designpatterns.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import nl.hanze.designpatterns.DAO.impl.db.LoginCredentialDAOImpl;
import nl.hanze.designpatterns.DAO.impl.db.TroubleTicketDAOImpl;
import nl.hanze.designpatterns.domain.LoginCredential;
import nl.hanze.designpatterns.domain.TroubleTicket;
import nl.hanze.designpatterns.view.DashboardView;
import nl.hanze.designpatterns.view.NewTicketView;

/**
 *
 * @author glenndeb
 */
public class NewTicketController{
    
    public NewTicketView view;
    private ActionListener onAddClick;

    public NewTicketController(final ContainerController parent) {
        this.view = new NewTicketView();
        parent.addTabbar("New Ticket",this.view);
        
        onAddClick = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                String date = view.getTitleText().getText();
                String username = view.getUsernameCallerText().getText();
                //String dateTime = "2013-10-13";
                String description = view.getDescriptionText().getText();
                
                java.util.Date date2 = null;
				try {
					date2 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                java.sql.Timestamp timestamp = new java.sql.Timestamp(date2.getTime());
               
                System.out.println("Ticket to add: " + date2 + username + description);
                
                TroubleTicket troubleTicket = new TroubleTicket(999 , timestamp ,parent.getUsername(), username, description, timestamp);
                TroubleTicketDAOImpl dao = new TroubleTicketDAOImpl();
                
                if(dao.saveTroubleTicket(troubleTicket)) {
                	//System.out.println("Trouble ticket added");
                	view.getTitleText().setText("Date: yyyy-mm-dd");
                	view.getUsernameCallerText().setText("Username of caller");
                	view.getDescriptionText().setText("Description");
                	
                } else {
                	System.out.println("Error: Trouble ticket could not be added.");
                }
                
                //LoginCredential loginCredential = new LoginCredential(username,password);
                //LoginCredentialDAOImpl dao = new LoginCredentialDAOImpl();
//                
//                try {
//                	if(dao.isValid(loginCredential)) {
//                		ContainerController container = new ContainerController();
//                		System.out.println("Valid credentials");
//                		loginForm.setVisible(false);
//                	} else {
//                		System.out.println("Invalid credentials");
//         
//                	}
//                    
//                } catch (Exception ex) {
//                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                

            }
        };
        view.getAddButton().addActionListener(onAddClick);
        
    }
    
    
    
}
