package org.my.homework.app.dao;

import org.my.homework.app.entities.CommonEntity;
import org.my.homework.app.entities.Movie;

import java.util.Date;
import java.util.List;

/**
 * Created by Alexander on 30-Apr-16.
 */
public interface MovieDAO extends CommonDAO<Movie> {

    List<Movie> moviesByShowingDate(Date date);
}
