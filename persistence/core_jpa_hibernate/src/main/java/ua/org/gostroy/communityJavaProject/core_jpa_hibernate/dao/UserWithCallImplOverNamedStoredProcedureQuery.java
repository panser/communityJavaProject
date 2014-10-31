package ua.org.gostroy.communityJavaProject.core_jpa_hibernate.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.org.gostroy.communityJavaProject.core_entity.entity.User;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Panov Sergey on 10/29/2014.
 */

public class UserWithCallImplOverNamedStoredProcedureQuery implements UserWithCallDao {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @PersistenceContext
    private EntityManager em;

    @Override
    public User procSimple(Long id) {
        StoredProcedureQuery query = em.createNamedStoredProcedureQuery("procSimple");
        query.setParameter("IN_ID", id);

        query.execute();

/*
        int updateCount = query.getUpdateCount();
        if (resultSet) {
            // Result sets must be processed first through getResultList() calls.
        }
*/

        String email = (String)query.getOutputParameterValue("OUT_EMAIL");
        String login = (String)query.getOutputParameterValue("OUT_LOGIN");
        String password = (String)query.getOutputParameterValue("OUT_PASSWORD");

        User user = new User();
        user.setId(id);
        user.setLogin(login);
        user.setEmail(email);
        user.setPassword(password);

        return user;
    }

    @Override
    public String funcSimple(Long id) {
        return null;
    }

    @Override
    public List<User> procOutRef() {
        StoredProcedureQuery query = em.createNamedStoredProcedureQuery("procOutRef");
        query.execute();   // not work with Oracle, bug https://hibernate.atlassian.net/browse/HHH-9286
//        List<User> result = query.getResultList();   //for MySQL

        List<User> result = (List)query.getOutputParameterValue("users");  //for Oracle
//        List<User> result = (List)query.getOutputParameterValue(1);
        return result;
    }

    @Override
    public BigDecimal procInArray(Long[] ids) {
        return null;
    }
}