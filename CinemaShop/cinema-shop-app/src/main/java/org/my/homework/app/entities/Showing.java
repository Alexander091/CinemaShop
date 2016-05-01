package org.my.homework.app.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Alexander on 30-Apr-16.
 */
@Entity
@Table(name = "showings")
public class Showing extends CommonEntity {
    private Hall hall;
    private Movie movie;
    private Date start;
    private List<Ticket> tickets;

    @ManyToOne
    @JoinColumn(name = "hall_id")
    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    @ManyToOne
    @JoinColumn(name = "movie_id")
    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Column(name = "start")
    @Type(type="timestamp")
    public Date getStart() {
        return start;
    }

    public void setStart(Date date) {
        this.start = date;
    }

    @OneToMany(mappedBy = "showing", cascade=CascadeType.REMOVE)
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

}
