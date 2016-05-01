package org.my.homework.web.controllers;

import org.apache.commons.codec.digest.DigestUtils;
import org.my.homework.app.dao.AuthDAO;
import org.my.homework.app.ejb.SecurityBean;
import org.my.homework.app.entities.Role;
import org.my.homework.app.entities.User;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.IOException;

/**
 * Created by Alexander on 01-May-16.
 */
@ManagedBean(name = "baseController")
@SessionScoped
public class BaseController {

    private User user;
//    @EJB
//    SecurityBean securityBean;
    @EJB
    AuthDAO authDAO;

//    @PostConstruct
    protected void redirectToLogin(){
        if(user == null) {
            try {
                FacesContext
                        .getCurrentInstance()
                        .getExternalContext()
                        .redirect("/cinemashop/faces/authorization/login.xhtml");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String getUserName(){
//        User user = securityBean.getUser();
        if(user == null) {
            return null;
        }
        return user.getLogin();
    }

    private String login;
    private String password;

    public void jaasLogout() throws IOException {
        user = null;
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("user", null);
        FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .redirect("/cinemashop/faces/authorization/login.xhtml");
    }

    public void checkLogin(ActionEvent actionEvent) throws IOException {

        String hashedPassword = DigestUtils.sha256Hex(password);
        User user = authDAO.getUserByLoginAndPassword(login, hashedPassword);
//        securityBean.setUser(user);
        this.user = user;
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("user", user);
        if(user != null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/cinemashop/faces/index.xhtml");
        }

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean hasUserSalesmanRole(){
        return hasUserRole("salesman");
    }

    public boolean hasUserManagerRole(){
        return hasUserRole("manager");
    }
    private boolean hasUserRole(String role){
        if(user == null) {
            return false;
        } else {
            for (Role userRole : user.getRoles()) {
                if(userRole.getName().equals(role)) {
                    return true;
                }
            }
        }
        return false;
    }
}


