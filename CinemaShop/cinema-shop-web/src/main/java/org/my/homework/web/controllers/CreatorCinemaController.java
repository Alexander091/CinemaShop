package org.my.homework.web.controllers;

import org.my.homework.app.dao.MovieDAO;
import org.my.homework.app.entities.Movie;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Alexander on 30-Apr-16.
 */
@ManagedBean(name = "creatorCinemaController")
public class CreatorCinemaController {
    private String title;
    private String year;
    private String duration;
    private String description;
    private String id;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getYear() {
        return year;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDuration() {
        return duration;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Movie save(){
        Movie movie = new Movie();
        if(id != null && !id.isEmpty()) {
            movie.setId(new Long(id));
        }
        movie.setDescription(description);
        movie.setTitle(title);
        movie.setDuration(Integer.parseInt(duration));
        movie.setYearOfProduction(Integer.parseInt(year));

        clearField();
        return movie;
    }

    private void clearField(){
        description = null;
        title = null;
        duration = null;
        year = null;
    }

    public void setMovie(Movie movie) {
        id = movie.getId().toString();
        description = movie.getDescription();
        title = movie.getTitle();
        duration = String.valueOf(movie.getDuration());
        year = String.valueOf(movie.getYearOfProduction());
    }

    public void handleClose(CloseEvent event) {
        clearField();
    }
}
