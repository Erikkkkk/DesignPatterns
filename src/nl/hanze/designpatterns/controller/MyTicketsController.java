/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hanze.designpatterns.controller;

import nl.hanze.designpatterns.view.MyTicketsView;

/**
 *
 * @author glenndeb
 */
public class MyTicketsController  {
    
    public MyTicketsView view;

    public MyTicketsController(ContainerController parent) {
        this.view = new MyTicketsView();
        parent.addTabbar("My Tickets",this.view);
    }
    
    
    
}
