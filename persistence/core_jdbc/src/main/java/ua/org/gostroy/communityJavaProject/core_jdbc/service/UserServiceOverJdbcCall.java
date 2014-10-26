package ua.org.gostroy.communityJavaProject.core_jdbc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.org.gostroy.communityJavaProject.core_entity.entity.User;
import ua.org.gostroy.communityJavaProject.core_jdbc.dao.UserDao;
import ua.org.gostroy.communityJavaProject.core_jdbc.dao.UserOverCallDao;

/**
 * Created by Panov Sergey on 10/26/2014.
 */
@Service
@Transactional
public class UserServiceOverJdbcCall {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    UserOverCallDao userDAO;

    @Transactional(readOnly = false)
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
}
