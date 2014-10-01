package ua.org.gostroy.communityJavaProject.core_jpa_hibernate.service;

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
import ua.org.gostroy.communityJavaProject.core_entity.model.User;

import java.util.List;
import java.util.Set;

/**
 * Created by Panov Sergey on 9/29/2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/ua/org/gostroy/communityJavaProject/core_jpa_hibernate/applicationContext.xml"})
public class UserImplHibernateTest {
    private final Logger LOG = LoggerFactory.getLogger(getClass());
    User testUser;

    @Autowired
    UserServiceOverJpaHibernate userService;

    @Before
    public void setup(){
        testUser = new User();
        testUser.setLogin("core_hibernate_jpa:testSetup");
        testUser = userService.save(testUser);
    }

    @After
    public void destroy(){
        userService.delete(testUser);
    }

    @Test
    public void findAll(){
        List<User> users = userService.findAll();
        Assert.assertNotEquals(users.size(), 0);
    }

    @Test
    public void update(){
        testUser.setLogin("core_hibernate_jpa:testUpdate");
        User updateUser = userService.update(testUser);
        LOG.trace(getClass() + ": update(), testUser = " + testUser);
        LOG.trace(getClass() + ": update(), updateUser = " + updateUser);
        Assert.assertEquals(testUser.getId(),updateUser.getId());
    }

    @Test
    public void save(){
        testUser.setLogin("core_hibernate_jpa:testSave");
        User saveUser = userService.save(testUser);
        LOG.trace(getClass() + ": save(), testUser = " + testUser);
        LOG.trace(getClass() + ": save(), saveUser = " + saveUser);
        Assert.assertEquals(testUser.getId(), saveUser.getId());
    }

}
