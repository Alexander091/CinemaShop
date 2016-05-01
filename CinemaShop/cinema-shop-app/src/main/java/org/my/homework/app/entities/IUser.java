package org.my.homework.app.entities;

/**
 * Created by Alexander on 01-May-16.
 */
public interface IUser extends ICommonEntity{

    String getLogin();
    void setLogin(String login);
}
