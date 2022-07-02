package com.oauth.security.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@ConfigurationProperties(prefix = "nginx")
@Data
@RefreshScope
@Configuration
public class NginxProperties {

	private String scheme;
	private String host;
	private String path;
	private int port;

}