package ua.org.gostroy.communityJavaProject.core_jdbc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.org.gostroy.communityJavaProject.core_entity.entity.User;
import ua.org.gostroy.communityJavaProject.core_jdbc.dao.UserDao;
import ua.org.gostroy.communityJavaProject.core_jdbc.dao.UserOverCallDao;

import java.util.List;

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
    public User procSimple(Long id) {
        LOG.trace(getClass() + " : procSimple ... ");
        User user = userDAO.procSimple(id);
        LOG.trace(getClass() + " : procSimple. ");
        return user;
    }

    public String funcSimple(Long id) {
        LOG.trace(getClass() + " : funcSimple ... ");
        String result = userDAO.funcSimple(id);
        LOG.trace(getClass() + " : funcSimple. ");
        return result;
    }

    public List<User> procOutRef() {
        LOG.trace(getClass() + " : procOutRef ... ");
        List<User> result = userDAO.procOutRef();
        LOG.trace(getClass() + " : procOutRef. ");
        return result;
    }
}
