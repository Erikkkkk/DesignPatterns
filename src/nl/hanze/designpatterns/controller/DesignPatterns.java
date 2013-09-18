package nl.hanze.designpatterns.controller;

import nl.hanze.designpatterns.view.LoginForm;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author glenndeb
 */
public class DesignPatterns {
    

        /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        System.out.println("hallooo");
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginController();
            }
        });
    }
}
