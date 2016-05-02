package org.my.homework.web.controllers;

import org.my.homework.app.dao.CommonDAO;
import org.my.homework.app.dao.MovieDAO;
import org.my.homework.app.dao.ShowingDAO;
import org.my.homework.app.entities.Hall;
import org.my.homework.app.entities.HallRow;
import org.my.homework.app.entities.Movie;
import org.my.homework.app.entities.Showing;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Alexander on 30-Apr-16.
 */
@ManagedBean(name = "showingManagerController")
@ViewScoped
public class ShowingManagerController extends BaseTableController<Showing> {

    private List<Showing> showings;

    @EJB
    private CommonDAO commonDAO;

    private Object getMoviesByShowingDate1;

    @PostConstruct
    private void init(){
        showings = commonDAO.getAllByClass(Showing.class);
    }

    public List<Showing> getShowings() {
        return showings;
    }

    public void setShowings(List<Showing> showings) {
        this.showings = showings;
    }

    public void onDeleteRow() {
        if(getSelectedEntity() != null) {
            commonDAO.delete(getSelectedEntity());
            showings.remove(getSelectedEntity());
        }
    }

    public void onSaveRow(Showing entity){
        boolean canSave = false;
        FacesMessage message = null;

        if(entity.getId() != null){
            findAndReplaceEntityById(entity);
        } else {
            showings.add(entity);
        }
        commonDAO.saveOrUpdate(entity);

        canSave = true;
        RequestContext context = RequestContext.getCurrentInstance();
        message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Saved successfully", "");
        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("saveResult", canSave);
    }

    public List<Hall> getAllHalls(){
        return commonDAO.getAllByClass(Hall.class);
    }

    public List<Movie> getAllMovies(){
        return commonDAO.getAllByClass(Movie.class);
    }


    public List<Showing> getShowingByDateAndMovie(){
        return null;
    }

    public void goToShowingView(SelectEvent event) throws IOException {
        FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .redirect("/cinemashop/faces/cinema/ticketView.xhtml?showingId=" + getSelectedEntity().getId());
    }

    private void findAndReplaceEntityById(Showing updatedEntity){
        for (Showing entity: showings) {
            if(entity.getId().equals(updatedEntity.getId())) {
                entity.setHall(updatedEntity.getHall());
                entity.setMovie(updatedEntity.getMovie());
                entity.setStart(updatedEntity.getStart());
                break;
            }
        }
    }

    public String getOccupation(Showing showing){
        int totalSeats = 0;
        for (HallRow hall: showing.getHall().getHallRows() ) {
            totalSeats+=hall.getSeats().size();
        }
        return showing.getTickets().size() + "/" + totalSeats;

    }
}
