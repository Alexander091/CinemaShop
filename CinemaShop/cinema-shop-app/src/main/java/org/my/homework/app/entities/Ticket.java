package org.my.homework.app.entities;

import javax.persistence.*;

/**
 * Created by Alexander on 30-Apr-16.
 */
@Entity
@Table(name = "tickets")
public class Ticket extends CommonEntity {

    private Showing showing;
    private Long seatId;
    private String name;

    @ManyToOne
    @JoinColumn(name = "showing_id")
    public Showing getShowing() {
        return showing;
    }

    public void setShowing(Showing showingId) {
        this.showing = showingId;
    }

    @Basic
    @Column(name="seat_id")
    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    @Basic
    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
