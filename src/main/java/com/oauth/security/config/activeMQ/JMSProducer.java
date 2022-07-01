package com.oauth.security.config.activeMQ;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JMSProducer {

	@Autowired
	JmsTemplate jmsTemplate;

	@Value("${active-mq.topic:test}")
	private String topic;

	public void sendMessage(final String queueName, final String message) {

		jmsTemplate.send(queueName, new MessageCreator() {

			public Message createMessage(Session session) throws JMSException {
				TextMessage message = session.createTextMessage();
				return message;
			}
		});
	}

	public void sendMessage(String message) {
		try {
			log.info("Attempting Send message to Topic: " + topic);
			jmsTemplate.convertAndSend(topic, message);
		} catch (Exception e) {
			log.error("Recieved Exception during send Message: ", e);
		}
	}

}
