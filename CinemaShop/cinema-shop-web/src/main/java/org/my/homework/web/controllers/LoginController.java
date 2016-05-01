package org.my.homework.web.controllers;

import org.apache.commons.codec.digest.DigestUtils;
import org.my.homework.app.dao.AuthDAO;
import org.my.homework.app.ejb.SecurityBean;
import org.my.homework.app.entities.User;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.IOException;

/**
 * Created by Alexander on 26-Apr-16.
 */
@ManagedBean(name = "loginController")
@ViewScoped
public class LoginController {

//    private String login;
//    private String password;
//
//    @EJB
//    AuthDAO authDAO;
//    @EJB
//    SecurityBean securityBean;
//
//    public void jaasLogout(){
//
//    }
//
//    public void checkLogin(ActionEvent actionEvent) throws IOException {
//        String hashedPassword = DigestUtils.sha256Hex(password);
//        User user = authDAO.getUserByLoginAndPassword(login, hashedPassword);
//        securityBean.setUser(user);
//        if(user != null) {
//            FacesContext.getCurrentInstance().getExternalContext().redirect("/cinemashop/faces/index.xhtml");
//        }
//    }
//
//    public String getLogin() {
//        return login;
//    }
//
//    public void setLogin(String login) {
//        this.login = login;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
}
