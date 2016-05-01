package org.my.homework.web.controllers;

import javax.faces.bean.ManagedBean;

/**
 * Created by Alexander on 26-Apr-16.
 */
@ManagedBean(name = "loginController")
public class LoginController {
    private String jaasUser="";

    public String getJaasUser() {
        return jaasUser;
    }

    public void jaasLogout(){

    }
}
