//package com.oauth.security.config.activeMQ;
//
//import javax.jms.JMSException;
//import javax.jms.Message;
//import javax.jms.Session;
//import javax.jms.TextMessage;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jms.core.JmsTemplate;
//import org.springframework.jms.core.MessageCreator;
//import org.springframework.stereotype.Service;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@Service
//public class JMSProducer {
//
//	@Autowired
//	private JmsTemplate jmsTemplate;
//
//	public void sendMessage(final String queueName, final String msg) {
//		log.info("-----Sending msg to queue in activeMQ");
//		try {
//			jmsTemplate.send(queueName, new MessageCreator() {
//
//				public Message createMessage(Session session) throws JMSException {
//					log.info("-----Creating message and sending the same to queue : {}", queueName);
//					TextMessage message = session.createTextMessage(msg);
//					return message;
//				}
//			});
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//}
