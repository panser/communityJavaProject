package ua.org.gostroy.communityJavaProject.core_entity.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Panov Sergey on 9/29/2014.
 */

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "procSimple",
                procedureName = "PROC_SIMPLE",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "in_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "out_email", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "out_login", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "out_password", type = String.class),
                }
        ),
        @NamedStoredProcedureQuery(
                name = "procOutRef",
                procedureName = "PROC_OUT_REF"
//                resultClasses = void.class
//                parameters = {
//                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "users", type = void.class),
//                }
        )
})

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "procSimpleNative",
                query = "{ call PROC_SIMPLE(:in_id, :out_email, :out_login, :out_password) }",
                hints = {@QueryHint(name = "org.hibernate.callable", value = "true") }),

        @NamedNativeQuery(
                name = "procOutRefNative",
                query = "{ call PROC_OUT_REF(?) }",
                resultClass = User.class,
                hints = {@QueryHint(name = "org.hibernate.callable", value = "true") })

})

@Entity
@Table(name = "core_users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"login", "email"})
})
public class User implements Serializable {
//    for MySQL and other (except Oracle)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)

//    for Oracle and H2, HSQLDB
//    @SequenceGenerator(name="core_users_seq", sequenceName="CORE_USERS_SEQ", schema = "BEANS_CONFIG", allocationSize=1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "core_users_seq")

//    for ALL
    @TableGenerator(name="TABLE_GEN", table="SEQUENCE_TABLE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", pkColumnValue="core_users_seq")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")

//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
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