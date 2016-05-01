package org.my.homework.app.dao;

import org.my.homework.app.entities.Movie;
import org.my.homework.app.entities.Showing;

import java.util.Date;
import java.util.List;

/**
 * Created by Alexander on 01-May-16.
 */
public interface ShowingDAO extends CommonDAO<Showing> {
    List<Showing> getShowingByShowingDateAndMovieId(Date currentDate, Long movieId);
}
