package org.my.homework.app.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.my.homework.app.dao.AuthDAO;
import org.my.homework.app.entities.CommonEntity;
import org.my.homework.app.entities.IUser;
import org.my.homework.app.entities.User;
import org.my.homework.app.entities.UserNamedQuery;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by Alexander on 01-May-16.
 */
@Stateless
@Local(AuthDAO.class)
public class AuthDAOImpl extends CommonDAOImpl<CommonEntity> implements AuthDAO {

    @Override
    public User getUserByLoginAndPassword(String login, String password) {
        Session session = sessionFactory.openSession();
        String hql = "FROM User as N WHERE login = :userName and password = :password";
        Query query = session.createQuery(hql);
        query.setParameter("userName", login);
        query.setParameter("password", password);
        List<User> result = query.list();
        session.close();
        User user = null;
        if (result != null && result.size() != 0){
            if (result.size() == 1){
                user = result.get(0);
            }
        }
        return user;
    }

    @Override
    public List<IUser> getSalesmans(Long currentUser) {
        Session session = sessionFactory.openSession();
        List<IUser> userNamedQueries = session.getNamedQuery("getSalesmans").setParameter("currentUser", currentUser).list();
        session.close();
        return userNamedQueries;
    }
}
