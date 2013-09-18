/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hanze.designpatterns.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import nl.hanze.designpatterns.view.LoginForm;
/**
 *
 * @author glenndeb
 */
public class LoginController {
    
        private LoginForm loginForm;
        private ActionListener onLoginClick;
        
        public LoginController(){
            loginForm = new LoginForm();
            loginForm.setVisible(true);
            
            onLoginClick = new ActionListener() {
              public void actionPerformed(ActionEvent actionEvent) {    
                  System.out.println("click");
              }
        };   
            this.loginForm.getLoginButton().addActionListener(onLoginClick);
        }
                
                
}
