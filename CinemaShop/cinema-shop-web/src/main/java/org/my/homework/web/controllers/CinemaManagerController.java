package org.my.homework.web.controllers;

import org.my.homework.app.dao.CommonDAO;
import org.my.homework.app.dao.MovieDAO;
import org.my.homework.app.entities.CommonEntity;
import org.my.homework.app.entities.Movie;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by Alexander on 30-Apr-16.
 */
@ManagedBean(name = "cinemaManagerController")
@ViewScoped
public class CinemaManagerController extends BaseTableController<Movie>{
    private Date currentDate = new Date();
    private List<Movie> movies;
    @EJB
    private CommonDAO commonDAO;
    @EJB
    private MovieDAO movieDAO;

    @PostConstruct
    private void init(){
        movies = commonDAO.getAllByClass(Movie.class);
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies= movies;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public void onDeleteRow() {
        if(getSelectedEntity() != null) {
            commonDAO.delete(getSelectedEntity());
            movies.remove(getSelectedEntity());
        }
    }

    public void onSaveRow(Movie entity){
        boolean canSave = false;
        FacesMessage message = null;

        commonDAO.saveOrUpdate(entity);
        movies.add(entity);

        canSave = true;
        RequestContext context = RequestContext.getCurrentInstance();
        message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Saved successfully", "");
        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("saveResult", canSave);

    }

    public List<Movie> getMoviesByShowingDate() {
        List<Movie> moviesByShowingDate;
        if(currentDate == null ) {
            moviesByShowingDate = commonDAO.getAllByClass(Movie.class);
        } else {
            moviesByShowingDate = movieDAO.moviesByShowingDate(currentDate);
        }
        return moviesByShowingDate;
    }

    public void goToShowingView(SelectEvent event) throws IOException {
        String dateString = new SimpleDateFormat("yyyyMMddHHmmss").format(currentDate);
        FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .redirect("/cinemashop/faces/cinema/showingView.xhtml?movieId=" + getSelectedEntity().getId()+"&currentDate=" + dateString);
    }
}
