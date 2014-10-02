package ua.org.gostroy.communityJavaProject.core_hibernate.dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ua.org.gostroy.communityJavaProject.core_entity.model.User;

import java.util.List;

/**
 * Created by Panov Sergey on 10/2/2014.
 */
//@Repository
public class UserImplHibernateOverHql implements UserDao {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public User findOne(Long id) {
        LOG.trace(getClass() + " : findOne ... ");
        String qlString = "SELECT e FROM User e WHERE e.id = :id";
        Query query = sessionFactory.getCurrentSession().createQuery(qlString);
        query.setParameter("id", id);
        User user =  (User)query.uniqueResult();
        if (user != null) {
            LOG.trace(getClass() + " : findOne. ");
        }
        LOG.trace(getClass() + " : findOne. Not found.");
        return user;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> findAll() {
        LOG.trace(getClass() + " : findAll ... ");
        List<User> resulyList = sessionFactory.getCurrentSession().createQuery("from User").list();
        LOG.trace(getClass() + " : findAll. ");
        return resulyList;
    }

    //  for test save(), persist(), update(), merge(), saveOrUpdate()
//    http://stackoverflow.com/questions/161224/what-are-the-differences-between-the-different-saving-methods-in-hibernate
    @Override
    public User save(User user) {
        LOG.trace(getClass() + " : save ... ");
//        User newUser = (User)sessionFactory.getCurrentSession().merge(user);    // create a lof of new Object, even if id != null
//        sessionFactory.getCurrentSession().save(user);    // create new Object, even if id != null
        sessionFactory.getCurrentSession().saveOrUpdate(user);    // not create new Object if id != null
        LOG.trace(getClass() + " : save. ");
        return user;
    }

    @Override
    public User update(User user) {
        LOG.trace(getClass() + " : update ... ");
        sessionFactory.getCurrentSession().update(user);
        LOG.trace(getClass() + " : update. ");
        return user;
    }

    @Override
    public void delete(User user) {
        LOG.trace(getClass() + " : delete ... ");
        sessionFactory.getCurrentSession().delete(user);
        LOG.trace(getClass() + " : delete. ");
    }
}
