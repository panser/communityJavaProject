package ua.org.gostroy.communityJavaProject.core_jdbc.service;

import org.junit.Assert;
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
    UserServiceOverJdbcCall userService;

    @Test
    public void procSimple(){
        User user = userService.procSimple(1L);
        Assert.assertNotNull(user);
    }

    @Test
    public void funcSimple(){
        String result = userService.funcSimple(1L);
        Assert.assertNotNull(result);
    }

    @Test
    public void procOutRef(){
        List<User> result = userService.procOutRef();
//        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 2);
    }
}
