//package com.oauth.security.config.activeMQ;
//
//import org.apache.activemq.ActiveMQConnectionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
//import org.springframework.jms.core.JmsTemplate;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@Configuration
//@ConditionalOnProperty(prefix = "active-mq", name = "enable", havingValue = "true")
//public class ActiveMQConfig {
//
//	@Autowired
//	private ActiveMQProperties jmsProperties;
//
//	@Bean
//	public ActiveMQConnectionFactory connectionFactory() {
//		log.info("----Creating connectionFactory for activeMQ");
//		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
//		connectionFactory.setBrokerURL(jmsProperties.getBroker_url());
//		connectionFactory.setPassword(jmsProperties.getPassword());
//		connectionFactory.setUserName(jmsProperties.getUser());
//		return connectionFactory;
//	}
//
//	@Bean
//	public JmsTemplate jmsTemplate() {
//		log.info("----Creating jmsTemplate for activeMQ");
//		JmsTemplate template = new JmsTemplate();
//		template.setConnectionFactory(connectionFactory());
//		template.setPubSubDomain(true);
//		return template;
//	}
//
//	@Bean
//	public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
//		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
//		factory.setConnectionFactory(connectionFactory());
//		factory.setConcurrency("1-1");
//		factory.setPubSubDomain(true);
//		return factory;
//	}
//
//}
