package com.oauth.security.config.activeMQ;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class JMSListener {

	@JmsListener(destination = "standalone-activemq-queue")
	@SendTo("outbound.queue")
	public String receiveMessageFromQueue(final Message jsonMessage) throws JMSException {
		String messageData = null;
		System.out.println("Received message " + jsonMessage);
		String response = null;
		if (jsonMessage instanceof TextMessage) {
			TextMessage textMessage = (TextMessage) jsonMessage;
			messageData = textMessage.getText();
			System.out.println(messageData);
		}
		return response;
	}

	@JmsListener(destination = "inbound.topic")
	@SendTo("outbound.topic")
	public String receiveMessageFromTopic(final Message jsonMessage) throws JMSException {
		String messageData = null;
		System.out.println("Received message " + jsonMessage);
		String response = null;
		if (jsonMessage instanceof TextMessage) {
			TextMessage textMessage = (TextMessage) jsonMessage;
			messageData = textMessage.getText();
			System.out.println(messageData);
		}
		return response;
	}
}

//
//@Component
//@Slf4j
//public class JmsConsumer implements MessageListener {
//
//
//    @Override
//    @JmsListener(destination = "${active-mq.topic}")
//    public void onMessage(Message message) {
//        try{
//            ObjectMessage objectMessage = (ObjectMessage)message;
//            Employee employee = (Employee)objectMessage.getObject();
//            //do additional processing
//           log.info("Received Message: "+ employee.toString());
//        } catch(Exception e) {
//          log.error("Received Exception : "+ e);
//        }
//
//    }
//}