package ua.org.gostroy.communityJavaProject.test.jms_activemq.alerts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.JmsUtils;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

/**
 * Created by Panov Sergey on 10/1/2014.
 */
public class UserAlertHandler {

    @Autowired
    JmsTemplate jmsTemplate;

    public String processAlert(){
        try {
            ObjectMessage receivedMessage = (ObjectMessage) jmsTemplate.receive();
            return (String) receivedMessage.getObject();
        } catch (JMSException jmsException) {
            throw JmsUtils.convertJmsAccessException(jmsException);
        }
    }
}
