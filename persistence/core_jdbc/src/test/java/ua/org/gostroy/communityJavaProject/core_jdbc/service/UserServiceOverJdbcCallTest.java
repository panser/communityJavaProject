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
    public void findOne(){
        User user = userService.findById(2L);
        Assert.assertEquals(user.getLogin(), "2");
    }
}
