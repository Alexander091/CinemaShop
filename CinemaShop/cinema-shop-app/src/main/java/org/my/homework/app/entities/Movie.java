package org.my.homework.app.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Alexander on 30-Apr-16.
 */
@Entity
@Table(name = "movies")
public class Movie extends CommonEntity {
    private String title;
    private int yearOfProduction;
    private int duration;
    private String description;
    private List<Showing> showings;

    @Basic
    @Column(name="title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name="year_of_production")
    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    @Basic
    @Column(name="duration")
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Basic
    @Column(name="description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(mappedBy = "movie", cascade=CascadeType.REMOVE)
    public List<Showing> getShowings() {
        return showings;
    }

    public void setShowings(List<Showing> showings) {
        this.showings = showings;
    }
}
