package com.oauth.security.config.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RabbitMQSender {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	@Autowired
	private RabbitMQProperties rabbitMQProperties;

	public void send(String msg) {
		rabbitTemplate.convertAndSend(rabbitMQProperties.getExchange(), rabbitMQProperties.getRoutingkey(), msg);
		log.info("-----Message to be sent to the queue : {}", msg);

	}
}