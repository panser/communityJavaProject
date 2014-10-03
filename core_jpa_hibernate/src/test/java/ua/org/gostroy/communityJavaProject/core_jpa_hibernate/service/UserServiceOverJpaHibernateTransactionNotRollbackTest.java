package ua.org.gostroy.communityJavaProject.core_jpa_hibernate.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ua.org.gostroy.communityJavaProject.core_entity.entity.User;

/**
 * Created by Panov Sergey on 10/2/2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/ua/org/gostroy/communityJavaProject/core_jpa_hibernate/applicationContext.xml"})
@TransactionConfiguration(defaultRollback = false)
@Transactional
public class UserServiceOverJpaHibernateTransactionNotRollbackTest {
    private final Logger LOG = LoggerFactory.getLogger(getClass());
    User testUser;

    @Autowired
    UserServiceOverJpaHibernate userService;

    @BeforeTransaction
    public void beforeTransaction() {
    }

    @Test
    public void save(){
        testUser = new User();
        testUser = userService.save(testUser);
        LOG.trace(getClass() + ": save(). testUser = " + testUser);
        Assert.assertNotNull(userService.findById(testUser.getId()));
    }

    @AfterTransaction
    public void afterTransaction() {
        Assert.assertNotNull(userService.findById(testUser.getId()));
        userService.delete(testUser);
    }

}
