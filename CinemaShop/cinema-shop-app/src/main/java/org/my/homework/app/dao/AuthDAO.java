package org.my.homework.app.dao;

import org.my.homework.app.entities.CommonEntity;
import org.my.homework.app.entities.IUser;
import org.my.homework.app.entities.Role;
import org.my.homework.app.entities.User;

import java.util.List;

/**
 * Created by Alexander on 01-May-16.
 */
public interface AuthDAO extends CommonDAO <CommonEntity> {

    User getUserByLoginAndPassword(String login, String password);

    List<IUser> getSalesmans(Long currentUser);

    Role getSalesmanRole();
}
