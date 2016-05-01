package org.my.homework.web.controllers;

import org.my.homework.app.dao.MovieDAO;
import org.my.homework.app.entities.Movie;
import org.primefaces.context.RequestContext;

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

    public Movie save(){
        Movie movie = new Movie();

        movie.setDescription(description);
        movie.setTitle(title);
        movie.setDuration(Integer.valueOf(duration));
        movie.setYearOfProduction(Integer.valueOf(year));

        clearField();
        return movie;
    }

    private void clearField(){
        description = null;
        title = null;
        duration = null;
        year = null;
    }
}
