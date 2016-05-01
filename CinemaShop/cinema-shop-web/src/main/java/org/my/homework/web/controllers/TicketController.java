package org.my.homework.web.controllers;

import org.my.homework.app.dao.CommonDAO;
import org.my.homework.app.entities.Hall;
import org.my.homework.app.entities.Showing;
import org.my.homework.app.entities.Ticket;
import org.primefaces.event.CloseEvent;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Alexander on 01-May-16.
 */
@ManagedBean(name = "ticketController")
@ViewScoped
public class TicketController{

    private Showing showing;
    private Long showingId;
    @EJB
    private CommonDAO commonDAO;
    private Set<Long> seatIds = new HashSet<Long>();

    public void setShowingId(Long showingId) {
        this.showingId = showingId;
        showing = (Showing) commonDAO.getByIdByClass(showingId, Showing.class);
    }

    public Set<Long> getSeatIds() {
        return seatIds;
    }

    public void setSeatIds(Set<Long> seatIds) {
        this.seatIds = seatIds;
    }

    public Long getShowingId() {
        return showingId;
    }

    public Showing getShowing() {
        return showing;
    }

    public void setShowing(Showing showing) {
        this.showing = showing;
    }


    public void buyTickets(ActionEvent actionEvent) throws IOException {
        List<Ticket> tickets = new ArrayList<Ticket>();
        for (Long seatId : seatIds) {
            Ticket ticket = new Ticket();
            ticket.setSeatId(seatId);
            Showing showing = new Showing();
            showing.setId(showingId);
            ticket.setShowing(showing);
            tickets.add(ticket);
        }
        commonDAO.saveOrUpdate(tickets);
        showing.getTickets().addAll(tickets);
        seatIds.clear();
        FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .redirect("/cinemashop/faces/cinema/movieView.xhtml");
    }
    public void prepareToByTicket(Long currentSeatId){
        if(isReservedTicket(currentSeatId)){
            seatIds.remove(currentSeatId);
        } else {
            seatIds.add(currentSeatId);
        }
    }


    public boolean isEnable(Long seatId){
        boolean isEnable = true;
        for(Ticket ticket : showing.getTickets()) {
            if(ticket.getSeatId().equals(seatId)){
                isEnable = false;
                break;
            }
        }
        return isEnable;
    }

    public boolean isReservedTicket(Long seatId){
        return seatIds.contains(seatId);
    }

    public int getTotalPrice(){
        return showing.getPrice() * seatIds.size();
    }

    public void handleClose(CloseEvent event) {

    }

}
