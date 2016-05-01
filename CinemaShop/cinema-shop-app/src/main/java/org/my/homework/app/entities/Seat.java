package org.my.homework.app.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Alexander on 30-Apr-16.
 */
@Entity
@Table(name = "seats")
public class Seat extends CommonEntity {
    private HallRow hallRow;
    private String name;

    @ManyToOne
    @JoinColumn(name = "hall_row_id")
    public HallRow getHallRow() {
        return hallRow;
    }

    public void setHallRow(HallRow hallRow) {
        this.hallRow = hallRow;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
