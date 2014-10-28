package ua.org.gostroy.communityJavaProject.core_jdbc.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.org.gostroy.communityJavaProject.core_entity.entity.User;

import java.util.List;

/**
 * Created by Panov Sergey on 10/26/2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/ua/org/gostroy/communityJavaProject/core_jdbc/applicationContext.xml"})
public class UserServiceOverJdbcCallTest {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    User testUser;
    @Autowired
    UserServiceOverJdbcCall userServiceOverJdbcCall;
    @Autowired
    UserServiceOverJdbc userService;

    @Before
    public void setup(){
        LOG.trace(getClass() + ": setup() ...");
        testUser = new User();
        testUser.setLogin("core_jdbc:testSetup");
        testUser = userService.save(testUser);
        LOG.trace(getClass() + ": setup().");
    }

    @After
    public void destroy(){
        LOG.trace(getClass() + ": destroy() ...");
        LOG.trace(getClass() + ": destroy(), testUser = " + testUser);
        userService.delete(testUser);
        LOG.trace(getClass() + ": destroy().");
    }

    @Test
    public void procSimple(){
        User user = userServiceOverJdbcCall.procSimple(testUser.getId());
        Assert.assertEquals(user.getLogin(), testUser.getLogin());
    }

    @Test
    public void funcSimple(){
        String result = userServiceOverJdbcCall.funcSimple(testUser.getId());
        Assert.assertNotNull(result);
    }

    @Test
    public void procOutRef(){
        List<User> result = userServiceOverJdbcCall.procOutRef();
        Assert.assertNotNull(result);
    }
}
