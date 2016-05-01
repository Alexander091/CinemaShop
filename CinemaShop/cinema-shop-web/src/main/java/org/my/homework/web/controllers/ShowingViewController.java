package org.my.homework.web.controllers;

import org.my.homework.app.dao.CommonDAO;
import org.my.homework.app.dao.ShowingDAO;
import org.my.homework.app.entities.Movie;
import org.my.homework.app.entities.Showing;
import org.primefaces.event.SelectEvent;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.Date;
import java.util.List;

/**
 * Created by Alexander on 01-May-16.
 */
@ManagedBean(name = "showingViewController")
@ViewScoped
public class ShowingViewController{

    @EJB
    private CommonDAO commonDAO;

    @EJB
    private ShowingDAO showingDAO;

    private Long movieId;
    private Date currentDate;

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public List<Showing> getShowingByShowingDateAndMovieId() {
        List<Showing> moviesByShowingDate;
        if(currentDate == null ) {
            moviesByShowingDate = commonDAO.getAllByClass(Showing.class);
        } else {
            moviesByShowingDate = showingDAO.getShowingByShowingDateAndMovieId(currentDate, movieId);
        }
        return moviesByShowingDate;
    }
}
