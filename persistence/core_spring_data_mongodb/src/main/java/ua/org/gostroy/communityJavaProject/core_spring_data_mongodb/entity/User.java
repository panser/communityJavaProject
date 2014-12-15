package ua.org.gostroy.communityJavaProject.core_spring_data_mongodb.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Panov Sergey on 9/29/2014.
 */

@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String login;
    @Indexed
    private String email;
    private String password;

    public User() {
    }

    public User(User user) {
        login = user.getLogin();
        email = user.getEmail();
        password = user.getPassword();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
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
