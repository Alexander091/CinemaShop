package org.my.homework.app.dao;

import org.my.homework.app.entities.CommonEntity;
import org.my.homework.app.entities.User;

/**
 * Created by Alexander on 01-May-16.
 */
public interface AuthDAO extends CommonDAO <CommonEntity> {

    User getUserByLoginAndPassword(String login, String password);
}
