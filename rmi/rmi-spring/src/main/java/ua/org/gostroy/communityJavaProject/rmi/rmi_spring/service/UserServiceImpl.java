package ua.org.gostroy.communityJavaProject.rmi.rmi_spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.org.gostroy.communityJavaProject.rmi.rmi_spring.dao.UserDao;
import ua.org.gostroy.communityJavaProject.rmi.rmi_spring.model.User;

import java.util.List;

/**
 * Created by Panov Sergey on 9/29/2014.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    UserDao userDAO;

    @Transactional(readOnly = true)
    @Override
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
    @Override
    public List<User> findAll() {
        LOG.trace(getClass() + " : findAll ... ");
        List<User> users = userDAO.findAll();
        LOG.trace(getClass() + " : findAll.");
        return users;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public User save(final User user){
        LOG.trace(getClass() + " : save ... ");
        User newUser = userDAO.save(user);
        LOG.trace(getClass() + " : save.");
        return newUser;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public User update(final User user){
        LOG.trace(getClass() + " : update ... ");
        User newUser = userDAO.update(user);
        LOG.trace(getClass() + " : update.");
        return newUser;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(final User user){
        LOG.trace(getClass() + " : delete ... ");
        userDAO.delete(user);
        LOG.trace(getClass() + " : delete.");
    }
}
