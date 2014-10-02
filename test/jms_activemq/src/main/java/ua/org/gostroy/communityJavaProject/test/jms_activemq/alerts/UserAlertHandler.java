package ua.org.gostroy.communityJavaProject.test.jms_activemq.alerts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.JmsUtils;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

/**
 * Created by Panov Sergey on 10/1/2014.
 */
public class UserAlertHandler {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    JmsTemplate jmsTemplate;

    public String processAlert(){
        try {
            ObjectMessage receivedMessage = (ObjectMessage) jmsTemplate.receive();
            String message =  (String) receivedMessage.getObject();
            LOG.trace(getClass() + ": async recive message: " + message);
            return message;
        } catch (JMSException jmsException) {
            throw JmsUtils.convertJmsAccessException(jmsException);
        }
    }
}
