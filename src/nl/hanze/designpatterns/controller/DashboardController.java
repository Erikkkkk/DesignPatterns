/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hanze.designpatterns.controller;

import nl.hanze.designpatterns.view.DashboardView;

/**
 *
 * @author glenndeb
 */
public class DashboardController  {
    
    public DashboardView view;

    public DashboardController(ContainerController parent) {
        this.view = new DashboardView();
        parent.addTabbar("Dashboard",this.view);
    }
    
    
    
}
