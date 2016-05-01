package org.my.homework.web.controllers;

import org.apache.commons.codec.digest.DigestUtils;
import org.my.homework.app.entities.User;
import org.primefaces.event.CloseEvent;

import javax.faces.bean.ManagedBean;

/**
 * Created by Alexander on 01-May-16.
 */
@ManagedBean(name = "creatorSalesmanController")
public class CreatorSalesmanController {

    private String password;
    private String login;

    public User save(){
        User user = new User();
        String hashedPassword = DigestUtils.sha256Hex(password);
        user.setLogin(login);
        user.setPassword(hashedPassword);
        clearField();
        return user;
    }

    private void clearField(){
        password = null;
        login = null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void handleClose(CloseEvent event) {
        clearField();
    }
}
