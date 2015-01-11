package ua.org.gostroy.communityJavaProject.rmi.rmi_spring.client;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.org.gostroy.communityJavaProject.rmi.rmi_spring.model.User;
import ua.org.gostroy.communityJavaProject.rmi.rmi_spring.service.UserService;

import java.util.List;

/**
 * Created by Panov Sergey on 1/11/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/ua/org/gostroy/communityJavaProject/rmi/rmi_spring/applicationContext.xml"})
public class UserClientRmiTest {

    User testUser;
    @Autowired
    UserService userClientRmi;

    @Before
    public void setup(){
        testUser = new User();
        testUser.setLogin("rmi_spring:testSetupRmi");
        testUser = userClientRmi.save(testUser);
    }

    @After
    public void destroy(){
        userClientRmi.delete(testUser);
    }

    @Test
    public void findAll(){
        List<User> users = userClientRmi.findAll();
        Assert.assertNotEquals(users.size(), 0);
    }

    @Test
    public void update(){
        testUser.setLogin("rmi_spring:testUpdateRmi");
        User updateUser = userClientRmi.update(testUser);
        Assert.assertEquals(testUser.getId(),updateUser.getId());
    }

    @Test
    public void save(){
        testUser.setLogin("rmi_spring:testSaveRmi");
        User saveUser = userClientRmi.save(testUser);
        Assert.assertEquals(testUser.getId(), saveUser.getId());
    }

}
