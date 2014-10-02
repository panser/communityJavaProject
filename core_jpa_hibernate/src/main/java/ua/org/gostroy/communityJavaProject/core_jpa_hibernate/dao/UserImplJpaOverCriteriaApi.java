package ua.org.gostroy.communityJavaProject.core_jpa_hibernate.dao;

import org.hibernate.criterion.CriteriaQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.org.gostroy.communityJavaProject.core_entity.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Panov Sergey on 10/2/2014.
 */
public class UserImplJpaOverCriteriaApi implements UserDao {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @PersistenceContext
    private EntityManager em;

    @Override
    public User findOne(Long id) {
        LOG.trace(getClass() + " : findOne ... ");
        User user = em.find(User.class, id);
//        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
//        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
//        Root<User> root = criteriaQuery.from
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
