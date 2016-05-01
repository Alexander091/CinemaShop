package org.my.homework.app.ejb;

import org.my.homework.app.entities.User;

/**
 * Created by Alexander on 01-May-16.
 */
public interface SecurityBean {

    User getUser();
    void setUser(User user);
}
