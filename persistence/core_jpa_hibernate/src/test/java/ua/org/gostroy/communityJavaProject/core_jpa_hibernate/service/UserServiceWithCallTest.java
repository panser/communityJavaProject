package ua.org.gostroy.communityJavaProject.core_jpa_hibernate.service;

import org.junit.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.org.gostroy.communityJavaProject.core_entity.entity.User;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Panov Sergey on 10/29/2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/ua/org/gostroy/communityJavaProject/core_jpa_hibernate/applicationContext.xml"})
public class UserServiceWithCallTest {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    User testUser;
    @Autowired
    UserServiceWithCall userServiceWithCall;
    @Autowired
    UserServiceOverJpaHibernate userService;

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
    @Ignore
    public void procSimple(){
        User user = userServiceWithCall.procSimple(testUser.getId());
        Assert.assertEquals(user.getLogin(), testUser.getLogin());
    }

    @Test
    @Ignore
    public void funcSimple(){
        String result = userServiceWithCall.funcSimple(testUser.getId());
        Assert.assertNotNull(result);
    }

    @Test
//    @Ignore
    public void procOutRef(){
        List<User> result = userServiceWithCall.procOutRef();
        Assert.assertNotNull(result);
    }

    @Test
    @Ignore
    public void procInArray(){
        Long id = testUser.getId();
        BigDecimal result = userServiceWithCall.procInArray(new Long[]{id, id});
        Assert.assertEquals(result, new BigDecimal(0));
    }
}
