/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hanze.designpatterns.controller;

import javax.swing.JPanel;
import nl.hanze.designpatterns.view.ContainerView;
import javax.swing.JTabbedPane;

/**
 *
 * @author glenndeb
 */
public class ContainerController {
    
    public ContainerView container;
    public JTabbedPane tabPane;

    public ContainerController() {
        
        this.container = new ContainerView();
        container.setVisible(true);
        tabPane = this.container.getInnerPanel();
        new DashboardController(this); 
        new MyTicketsController(this);
        new NewTicketController(this);
        
    }
    
    public void addTabbar(String title, JPanel panel){
        tabPane.addTab(title, panel);
    }
    
    
    
}
