package org.my.homework.web.controllers;

import org.my.homework.app.dao.CommonDAO;
import org.my.homework.app.entities.Hall;
import org.my.homework.app.entities.Movie;
import org.my.homework.app.entities.Showing;
import org.primefaces.event.CloseEvent;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.event.AjaxBehaviorEvent;
import java.util.Date;

/**
 * Created by Alexander on 01-May-16.
 */
@ManagedBean(name = "creatorShowingController")
public class CreatorShowingController{

    private Date start;
    private Long hallId;
    private Long movieId;
    private String id;
    private String price;

    @EJB
    private CommonDAO commonDAO;

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getStart() {
        return start;
    }

    public void setHallId(Long hallId) {
        this.hallId = hallId;
    }

    public Long getHallId() {
        return hallId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Showing save(){
        Showing showing = new Showing();

        Movie movie = (Movie) commonDAO.getByIdByClass(movieId, Movie.class);
        Hall hall = (Hall) commonDAO.getByIdByClass(hallId, Hall.class);;

        if(id != null && !id.isEmpty()) {
            showing.setId(new Long(id));
        }
        showing.setStart(start);
        showing.setHall(hall);
        showing.setMovie(movie);
        showing.setPrice(Integer.parseInt(price));

        clearField();
        return showing;
    }

    private void clearField(){
        hallId = null;
        movieId = null;
        start = null;
        price = "0";
    }

    public void setShowing(Showing showing) {
        id = showing.getId().toString();
        hallId = showing.getHall().getId();
        movieId = showing.getMovie().getId();
        start = showing.getStart();
    }

    public void handleClose(CloseEvent event) {
        clearField();
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
