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

public class UserOverCallImplWithNamedStoredProcedureQuery implements UserOverCallDao {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @PersistenceContext
    private EntityManager em;

    @Override
    public User procSimple(Long id) {
        StoredProcedureQuery query = em.createNamedStoredProcedureQuery("procSimple");
        query.setParameter("in_id", id);
        String email = (String)query.getOutputParameterValue("out_email");
        String login = (String)query.getOutputParameterValue("out_login");
        String password = (String)query.getOutputParameterValue("out_password");

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
        query.registerStoredProcedureParameter(1, void.class, ParameterMode.REF_CURSOR);
        query.execute();
//        List<User> result = (List)query.getOutputParameterValue(1);
        List<User> result = (List)query.getOutputParameterValue("users");
//        List<User> result = query.getResultList();
        return result;
    }

    @Override
    public BigDecimal procInArray(Long[] ids) {
        return null;
    }
}