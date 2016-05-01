package org.my.homework.web.controllers;

import org.my.homework.app.dao.CommonDAO;
import org.my.homework.app.entities.Hall;
import org.my.homework.app.entities.Showing;
import org.my.homework.app.entities.Ticket;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Alexander on 01-May-16.
 */
@ManagedBean(name = "ticketController")
@ViewScoped
public class TicketController {

    private Showing showing;
    private Long showingId;
    @EJB
    private CommonDAO commonDAO;
    private List<Long> selectedTickets;
    private Set<Long> seatIds = new HashSet<Long>();

    public void setShowingId(Long showingId) {
        this.showingId = showingId;
        showing = (Showing) commonDAO.getByIdByClass(showingId, Showing.class);
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

    public void setSelectedTickets(List<Long> selectedTickets) {
        this.selectedTickets = selectedTickets;
    }

    public List<Long> getSelectedTickets() {
        return selectedTickets;
    }

    public void buyTickets(ActionEvent actionEvent){
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

}
