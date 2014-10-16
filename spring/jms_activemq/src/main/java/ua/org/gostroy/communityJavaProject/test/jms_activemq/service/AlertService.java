package ua.org.gostroy.communityJavaProject.test.jms_activemq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.support.JmsUtils;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

/**
 * Created by Panov Sergey on 10/1/2014.
 */
@Service
public class AlertService {

    @Autowired
    JmsTemplate jmsTemplate;

    public void sendAlert(final String message) {
        jmsTemplate.send(new MessageCreator() {
                             @Override
                             public Message createMessage(Session session) throws JMSException {
                                 return session.createObjectMessage(message);
                             }
                         }
        );
    }

    public String getAlert() {
        try {
            ObjectMessage receivedMessage = (ObjectMessage) jmsTemplate.receive();
            return (String) receivedMessage.getObject();
        } catch (JMSException jmsException) {
            throw JmsUtils.convertJmsAccessException(jmsException);
        }
    }
}
