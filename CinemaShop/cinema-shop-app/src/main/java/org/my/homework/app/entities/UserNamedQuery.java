package org.my.homework.app.entities;


import javax.persistence.*;

/**
 * Created by Alexander on 01-May-16.
 */
@NamedQueries(
        @NamedQuery(
                name = "getSalesmans",
//                query = "SELECT U FROM User AS U INNER JOIN UserRole UR ON UR.user = U AND UR.role.name = 'salesman'"
                query = "FROM User where id != :currentUser"
        )
)
@Entity
@Table(name = "USERS")
public class UserNamedQuery extends CommonEntity implements IUser{
    private String login;

    @Basic
    @Column(name="login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
