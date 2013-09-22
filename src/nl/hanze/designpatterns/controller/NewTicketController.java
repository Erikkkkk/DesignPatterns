/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hanze.designpatterns.controller;

import nl.hanze.designpatterns.view.DashboardView;
import nl.hanze.designpatterns.view.NewTicketView;

/**
 *
 * @author glenndeb
 */
public class NewTicketController  {
    
    public NewTicketView view;

    public NewTicketController(ContainerController parent) {
        this.view = new NewTicketView();
        parent.addTabbar("Nieuwe Ticket",this.view);
    }
    
    
    
}
