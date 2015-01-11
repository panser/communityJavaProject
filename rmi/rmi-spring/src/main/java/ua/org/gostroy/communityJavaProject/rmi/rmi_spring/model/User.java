package ua.org.gostroy.communityJavaProject.rmi.rmi_spring.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Panov Sergey on 9/29/2014.
 */

@Entity
@Table(name = "core_users")
public class User implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(precision = 4)
    private Long id;
    @Column(unique = true)
    private String login;
    @Column(unique = true)
    private String email;
    private String password;

    public User() {
    }

    public User(User user) {
        login = user.getLogin();
        email = user.getEmail();
        password = user.getPassword();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
