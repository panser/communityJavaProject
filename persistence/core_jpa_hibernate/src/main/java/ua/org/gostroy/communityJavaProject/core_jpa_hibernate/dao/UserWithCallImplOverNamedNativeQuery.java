package ua.org.gostroy.communityJavaProject.core_jpa_hibernate.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.org.gostroy.communityJavaProject.core_entity.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Panov Sergey on 10/29/2014.
 */
public class UserWithCallImplOverNamedNativeQuery implements UserWithCallDao {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @PersistenceContext
    private EntityManager em;

    @Override
    public User procSimple(Long id) {
        Query query = em.createNamedQuery("procSimpleNative");
        query.setParameter("in_id",id);

        query.executeUpdate();

        String email = (String)query.getParameterValue("out_email");
        String login = (String)query.getParameterValue("out_login");
        String password = (String)query.getParameterValue("out_password");

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
        Query query = em.createNamedQuery("procOutRefNative");
        List<User> result = query.getResultList();
        return result;
    }

    @Override
    public BigDecimal procInArray(Long[] ids) {
        return null;
    }
}
