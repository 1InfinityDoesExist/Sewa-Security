package com.oauth.security.config.activeMQ;

import java.util.Arrays;

import javax.jms.Queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
//@ConditionalOnProperty(prefix = "active-mq", name = "enable", havingValue = "true")
public class ActiveMQConfig {

	@Autowired
	private ActiveMQProperties properties;

	@Bean
	public Queue quque() {
		return new ActiveMQQueue("standalone-activemq-queue");
	}

	@Bean
	public ActiveMQConnectionFactory connectionFactory() {
		log.info("------AcitveMQ connection factory-----");
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setBrokerURL(properties.getBroker_url());
		connectionFactory.setPassword(properties.getPassword());
		connectionFactory.setUserName(properties.getUser());
		connectionFactory.setTrustedPackages(Arrays.asList("com.mailshine.springbootstandaloneactivemq"));
		return connectionFactory;
	}

	@Bean
	public JmsTemplate jmsTemplate() {
		log.info("------AcitveMQ jmsTemplate -----");
		JmsTemplate template = new JmsTemplate();
		template.setConnectionFactory(connectionFactory());
		template.setPubSubDomain(true);
		return template;
	}

	@Bean
	public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory());
		factory.setConcurrency("1-1");
		factory.setPubSubDomain(true);
		return factory;
	}

}
