package org.my.homework.app.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.my.homework.app.dao.CommonDAO;
import org.my.homework.app.entities.CommonEntity;
import org.my.homework.app.utils.HibernateUtil;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Дмитрий on 11.12.2015.
 */
@Stateless
@Local(CommonDAO.class)
public class CommonDAOImpl<T extends CommonEntity> implements CommonDAO<T> {
//    private Class<T> type;
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public CommonDAOImpl(){
//        Type t = getClass().getGenericSuperclass();
//        ParameterizedType pt = (ParameterizedType) t;
//        type = (Class) pt.getActualTypeArguments()[0];
    }
    public T getById(Long id){
//        Session session = sessionFactory.openSession();
//        T result = (T) session.get(type, id);
//        session.close();
//        return result;
        return null;
    }

    public void saveOrUpdate(CommonEntity object){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(object);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveOrUpdate(List<CommonEntity> objects) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        for (CommonEntity entity : objects) {
            session.saveOrUpdate(entity);
        }
        session.getTransaction().commit();
        session.close();
    }

    public List<T> getAll(){
//        Session session = sessionFactory.openSession();
//        List<T> result = session.createCriteria(type).list();
//        session.close();
//        return result;
        return null;
    }

    public void delete(CommonEntity object){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(object);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public T getByIdByClass(Long id, Class<? super CommonEntity> clazz) {
        Session session = sessionFactory.openSession();
        T result = (T) session.get(clazz, id);
        session.close();
        return result;
    }

    @Override
    public List<? extends CommonEntity> getAllByClass(Class<? super CommonEntity> clazz) {
        Session session = sessionFactory.openSession();
        List<T> result = session.createCriteria(clazz).list();
        session.close();
        return result;
    }
}
