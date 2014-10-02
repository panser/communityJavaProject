package ua.org.gostroy.communityJavaProject.test.jms_activemq.service;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.org.gostroy.communityJavaProject.core_entity.model.User;

/**
 * Created by Panov Sergey on 10/1/2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/ua/org/gostroy/communityJavaProject/test/jms_activemq/applicationContext.xml")
public class AlertServiceTest {
    private final Logger LOG = LoggerFactory.getLogger(getClass());
    User testUser;

    @Autowired
    AlertService alertService;

    @Test
//    @Ignore
    public void syncJmsTest(){
        LOG.trace(getClass() + ": syncJmsTest() ...");
        String sendMessage = "testJms";
        alertService.sendAlert(sendMessage);
        String receiveMessage = alertService.getAlert();
        Assert.assertEquals(sendMessage, receiveMessage);
        LOG.trace(getClass() + ": syncJmsTest().");
    }

    @Test
//    @Ignore
    public void asyncJmsTest(){
        LOG.trace(getClass() + ": asyncJmsTest() ...");
        String sendMessage = "testAJms";
        alertService.sendAlert(sendMessage);
        String receiveMessage = alertService.getAlert();
        Assert.assertEquals(sendMessage, receiveMessage);
        LOG.trace(getClass() + ": asyncJmsTest().");
    }

}
