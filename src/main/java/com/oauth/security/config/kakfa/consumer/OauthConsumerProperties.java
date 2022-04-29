package com.oauth.security.config.kakfa.consumer;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "oauth.kafka.consumer")
@RefreshScope
@Data
public class OauthConsumerProperties {

	private String bootstrapServers;
	private String groupId;
	private String autoOffsetReset;
	private String keyDeserializer;
	private String valueDeserializer;
	private String enableAutoCommit;
	private String autoCommitInterval;
	private String sessionTimeout;
	private int concurrency;
	private int timeout;
	private int poolSize;
}
