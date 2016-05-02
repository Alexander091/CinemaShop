package org.my.homework.app.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.my.homework.app.dao.ShowingDAO;
import org.my.homework.app.entities.Movie;
import org.my.homework.app.entities.Showing;
import org.my.homework.app.utils.HibernateUtil;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Alexander on 01-May-16.
 */
@Stateless
@Local(ShowingDAO.class)
public class ShowingDAOImpl extends CommonDAOImpl<Showing> {


    public List<Showing> getShowingByShowingDateAndMovieId(Date date, Long movieId) {
        Session session = sessionFactory.openSession();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);

        Date startDate = calendar.getTime();

        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date endTime = calendar.getTime();

        String query = "FROM Showing as s " +
                "Where s.movie.id = :movieId and s.start BETWEEN :stDate AND :edDate";

        List<Showing> showings = (List<Showing>) session.createQuery(query)
                .setParameter("movieId", movieId)
                .setParameter("stDate", startDate)
                .setParameter("edDate", endTime)
                .list();
        session.close();
        return showings;
    }

}
