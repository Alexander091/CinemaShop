package org.my.homework.app.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Alexander on 30-Apr-16.
 */
@Entity
@Table(name = "halls")
public class Hall extends CommonEntity {
    private String name;
    private List<HallRow> hallRows;

    @Basic
    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "hall", cascade=CascadeType.REMOVE)
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<HallRow> getHallRows() {
        return hallRows;
    }

    public void setHallRows(List<HallRow> hallRows) {
        this.hallRows = hallRows;
    }
}
