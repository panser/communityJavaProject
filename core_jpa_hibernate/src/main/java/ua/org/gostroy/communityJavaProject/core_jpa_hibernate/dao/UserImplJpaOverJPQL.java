package ua.org.gostroy.communityJavaProject.core_jpa_hibernate.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.org.gostroy.communityJavaProject.core_entity.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Panov Sergey on 10/2/2014.
 */
public class UserImplJpaOverJPQL implements UserDao {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @PersistenceContext
    private EntityManager em;

    @Override
    public User findOne(Long id) {
        LOG.trace(getClass() + " : findOne ... ");
        String qlString = "SELECT e FROM User e WHERE e.id = :id";
        TypedQuery<User> query = em.createQuery(qlString, User.class);
        query.setParameter("id", id);
        User user =  query.getSingleResult();
        if (user != null) {
            LOG.trace(getClass() + " : findOne. ");
        }
        LOG.trace(getClass() + " : findOne. Not found.");
        return user;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public void delete(User user) {

    }
}
