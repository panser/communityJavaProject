package ua.org.gostroy.communityJavaProject.test.jms_activemq.alerts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.JmsUtils;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Panov Sergey on 10/1/2014.
 */
public class UserAlertHandler implements MessageListener {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    JmsTemplate jmsTemplate;
    @Autowired
    private AtomicInteger counter = null;

/*
    public String processAlert(){
        try {
            ObjectMessage receivedMessage = (ObjectMessage) jmsTemplate.receive();
            String message =  (String) receivedMessage.getObject();
            LOG.trace(getClass() + ": Async recive message: " + message);
            counter.incrementAndGet();
            return message;
        } catch (JMSException jmsException) {
            throw JmsUtils.convertJmsAccessException(jmsException);
        }
    }
*/

    @Override
    public void onMessage(Message message) {
        try {
            ObjectMessage receivedMessage = (ObjectMessage) jmsTemplate.receive();
            String messageReceive =  (String) receivedMessage.getObject();
            LOG.trace(getClass() + ": Async recive message: " + messageReceive);
            counter.incrementAndGet();
        } catch (JMSException jmsException) {
            throw JmsUtils.convertJmsAccessException(jmsException);
        }
    }
}
