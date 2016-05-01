package org.my.homework.app.entities;

import javax.persistence.*;

/**
 * Created by Alexander on 01-May-16.
 */
@NamedQueries(
        @NamedQuery(
                name = "getSalesmanRole",
                query = "FROM Role where name = 'salesman'"
        )
)
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
