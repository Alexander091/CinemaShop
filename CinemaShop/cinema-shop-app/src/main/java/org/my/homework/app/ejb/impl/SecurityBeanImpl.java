package org.my.homework.app.ejb.impl;

import org.my.homework.app.ejb.SecurityBean;
import org.my.homework.app.entities.User;

import javax.ejb.Local;
import javax.ejb.Stateful;

/**
 * Created by Alexander on 01-May-16.
 */
@Stateful
@Local(SecurityBean.class)
public class SecurityBeanImpl implements SecurityBean {
    private User user;
    public User getUser(){
        return user;
    }
    public void setUser(User user){
        this.user = user;
    }
}
