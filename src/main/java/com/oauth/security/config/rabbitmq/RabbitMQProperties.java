package com.oauth.security.config.rabbitmq;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "sewa.rabbitmq")
public class RabbitMQProperties {

	private String queue;
	private String exchange;
	private String routingkey;

}
