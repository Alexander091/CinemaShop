package org.my.homework.app.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.my.homework.app.dao.MovieDAO;
import org.my.homework.app.entities.Movie;
import org.my.homework.app.utils.HibernateUtil;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Alexander on 30-Apr-16.
 */
@Stateless
@Local(MovieDAO.class)
public class MovieDAOImpl extends CommonDAOImpl<Movie> implements MovieDAO {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    @Override
    public List<Movie> moviesByShowingDate(Date date) {
        Session session = sessionFactory.openSession();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);

        Date startDate = calendar.getTime();

        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date endTime = calendar.getTime();

        String query = "select m FROM Movie as m " +
                "inner join m.showings as s " +
                "Where s.start BETWEEN :stDate AND :edDate";

        List<Movie> movies = (List<Movie>) session.createQuery(query)
                .setParameter("stDate", startDate)
                .setParameter("edDate", endTime)
                .list();

        return movies;
    }
}
