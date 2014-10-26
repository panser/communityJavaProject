package ua.org.gostroy.communityJavaProject.core_mybatis.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.org.gostroy.communityJavaProject.core_entity.entity.User;
import ua.org.gostroy.communityJavaProject.core_mybatis.dao.UserDao;

import java.util.List;

/**
 * Created by Panov Sergey on 9/29/2014.
 */
@Service
@Transactional
public class UserServiceOverMyBatis {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserDao userDAO;

    @Transactional(readOnly = true)
    public User findById(final Long id) {
        LOG.trace(getClass() + " : findUserById ... ");
        User user = userDAO.findOne(id);
        if (user != null) {
            LOG.trace(getClass() + " : findUserById. ");
            return user;
        }
        LOG.trace(getClass() + " : findUserById. Not found.");
        return null;
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        LOG.trace(getClass() + " : findAll ... ");
        List<User> users = userDAO.findAll();
        LOG.trace(getClass() + " : findAll.");
        return users;
    }

    @Transactional(rollbackFor = Exception.class)
    public User save(final User user) {
        LOG.trace(getClass() + " : save ... ");
        if(user.getId() == null) {
            userDAO.save(user);
//            Long id = userDAO.save(user);
//            user.setId(id);
        }
        LOG.trace(getClass() + " : save.");
        return user;
    }

    @Transactional(rollbackFor = Exception.class)
    public User update(final User user) {
        LOG.trace(getClass() + " : update ... ");
        userDAO.update(user);
        LOG.trace(getClass() + " : update.");
        return user;
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(final User user) {
        LOG.trace(getClass() + " : delete ... ");
        userDAO.delete(user);
        LOG.trace(getClass() + " : delete.");
    }
}
