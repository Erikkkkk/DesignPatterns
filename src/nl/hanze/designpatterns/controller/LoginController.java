/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hanze.designpatterns.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.hanze.designpatterns.DAO.LoginCredentialDAO;
import nl.hanze.designpatterns.DAO.impl.db.LoginCredentialDAOImpl;
import nl.hanze.designpatterns.domain.LoginCredential;
import nl.hanze.designpatterns.view.*;

/**
 *
 * @author glenndeb
 */
public class LoginController {

    private LoginForm loginForm;
    private ActionListener onLoginClick;

    public LoginController() {
        loginForm = new LoginForm();
        loginForm.setVisible(true);

        onLoginClick = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                String username = loginForm.getUsernameText().getText();
                String password = loginForm.getPasswordText().getText();

                System.out.println("Login pressed");
                
                LoginCredential loginCredential = new LoginCredential(username,password);
                
                LoginCredentialDAOImpl dao = new LoginCredentialDAOImpl();
                try {
                    if(dao.isValid(loginCredential)){
                        ContainerController container = new ContainerController();
                    }
                } catch (Exception ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                
            }
        };
        this.loginForm.getLoginButton().addActionListener(onLoginClick);
    }
}
