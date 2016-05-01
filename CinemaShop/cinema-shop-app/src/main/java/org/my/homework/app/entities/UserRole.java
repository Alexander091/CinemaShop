package org.my.homework.app.entities;

import javax.persistence.*;

/**
 * Created by Alexander on 01-May-16.
 */
@Entity
@Table(name = "users_roles")
public class UserRole extends CommonEntity{

    private User user;
    private Role role;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "role_id")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
