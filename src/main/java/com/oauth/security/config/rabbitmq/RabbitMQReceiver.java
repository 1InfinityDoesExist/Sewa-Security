package com.oauth.security.config.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RabbitMQReceiver {

	@RabbitListener(queues = { "sewa.queue" })
	public void receive1(String msg) throws InterruptedException {
		log.info("-----Message from rabbitmq : {}", msg);
	}

}
