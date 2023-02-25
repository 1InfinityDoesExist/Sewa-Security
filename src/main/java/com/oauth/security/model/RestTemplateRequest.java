package com.oauth.security.model;

import java.util.Map;

import org.springframework.http.HttpMethod;

import lombok.Data;

@Data
public class RestTemplateRequest {

	private boolean paged;
	private String url;
	private HttpMethod method;
	private Map<String, String> headers;
	private Map<String, Object> body;
	private String jsonPath;
}
