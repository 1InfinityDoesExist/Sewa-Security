package com.oauth.security.config.kakfa.producer;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "oauth.kafka.producer")
@RefreshScope
@Data
public class OauthProducerProperties {

	private String bootstarpServers;
	private String keySerializer;
	private String valueSerialzier;
	private String acks;
	private String retries;
	private String batchSize;
	private String bufferMemory;

}
