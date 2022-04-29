package com.oauth.security.config.elasticsearch;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "oauth.elastic")
public class ElasticProperties {

	private String elasticHost;
	private int elasticPort;

}
