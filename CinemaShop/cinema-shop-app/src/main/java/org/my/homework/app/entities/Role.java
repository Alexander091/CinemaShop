package org.my.homework.app.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Alexander on 01-May-16.
 */
@Entity
@Table(name = "roles")
public class Role extends CommonEntity {

    private String name;

    @Basic
    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
