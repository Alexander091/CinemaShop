package org.my.homework.app.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Alexander on 30-Apr-16.
 */
@Entity
@Table(name = "HALL_ROWS")
public class HallRow extends CommonEntity {

    private Hall hall;
    private String name;
    private List<Seat> seats;

    @ManyToOne
    @JoinColumn(name = "hall_id")
    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hallId) {
        this.hall = hallId;
    }

    @Basic
    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }


    @OneToMany(mappedBy = "hallRow", cascade=CascadeType.REMOVE)
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Seat> getSeats() {
        return seats;
    }
}
