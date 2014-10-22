package ua.org.gostroy.communityJavaProject.core_spring_data.service;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.org.gostroy.communityJavaProject.core_entity.entity.User;
import ua.org.gostroy.communityJavaProject.core_spring_data.repository.UserRepository;

import java.util.List;

/**
 * Created by Panov Sergey on 9/29/2014.
 */
@Service
@Transactional
public class UserServiceOverSpringData {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    UserRepository userDAO;

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
    public User save(final User user) throws ConstraintViolationException {
        LOG.trace(getClass() + " : save ... ");
        User userNew = userDAO.saveAndFlush(user);
        LOG.trace(getClass() + " : save.");
        return userNew;
    }

    @Transactional(rollbackFor = Exception.class)
    public User update(final User user) throws ConstraintViolationException {
        LOG.trace(getClass() + " : update ... ");
        User userNew = userDAO.saveAndFlush(user);
        LOG.trace(getClass() + " : update.");
        return userNew;
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(final User user) throws ConstraintViolationException {
        LOG.trace(getClass() + " : delete ... ");
        userDAO.delete(user);
        LOG.trace(getClass() + " : delete.");
    }
}