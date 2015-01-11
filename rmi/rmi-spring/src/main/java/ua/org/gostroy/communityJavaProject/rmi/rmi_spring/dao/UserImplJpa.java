package ua.org.gostroy.communityJavaProject.rmi.rmi_spring.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.org.gostroy.communityJavaProject.rmi.rmi_spring.dao.UserDao;
import ua.org.gostroy.communityJavaProject.rmi.rmi_spring.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Panov Sergey on 9/29/2014.
 */
@Service
public class UserImplJpa implements UserDao {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @PersistenceContext
    private EntityManager em;

    @Override
    public User findOne(Long id) {
        LOG.trace(getClass() + " : findOne ... ");
        User user = em.find(User.class, id);
        if (user != null) {
            LOG.trace(getClass() + " : findOne. ");
        }
        else {
            LOG.trace(getClass() + " : findOne. Not found.");
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        LOG.trace(getClass() + " : findAll ... ");
        Query query = em.createQuery("SELECT e FROM User e");
        List users = (List<User>) query.getResultList();
        LOG.trace(getClass() + " : findAll. ");
        return users;
    }

    @Override
    public User save(User user) {
        LOG.trace(getClass() + " : save ... ");
        User newUser = em.merge(user);
        LOG.trace(getClass() + " : save. ");
        return newUser;
    }

    @Override
    public User update(User user) {
        LOG.trace(getClass() + " : update ... ");
        User newUser = em.merge(user);
        LOG.trace(getClass() + " : update. ");
        return newUser;
    }

    @Override
    public void delete(User user) {
        LOG.trace(getClass() + " : delete ... ");
//        em.remove(user);
        em.remove(em.contains(user) ? user : em.merge(user));
        LOG.trace(getClass() + " : delete. ");
    }

    public void deleteUsers() {
        Query query = em.createNativeQuery("TRUNCATE TABLE users");
        query.executeUpdate();
    }
}
