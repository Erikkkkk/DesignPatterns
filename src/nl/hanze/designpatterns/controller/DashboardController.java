/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hanze.designpatterns.controller;

import nl.hanze.designpatterns.DAO.impl.file.ser.QuestionDaoImplSer;
import nl.hanze.designpatterns.domain.Question;
import nl.hanze.designpatterns.view.DashboardView;

/**
 *
 * @author glenndeb
 */
public class DashboardController  {
    private Question root;
    public DashboardView view;

    public DashboardController(ContainerController parent) {
    	QuestionDaoImplSer dao = new QuestionDaoImplSer();
        root = dao.getRootQuestion();
        
        this.view = new DashboardView(root);
        parent.addTabbar("Thesaurus",this.view);
    }
    
    
    
}
