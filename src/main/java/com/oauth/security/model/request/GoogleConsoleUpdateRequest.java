package com.oauth.security.model.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.ToString;

@lombok.Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleConsoleUpdateRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4360669522089074262L;
	private String googleAppId;
	private String googleAppSecret;
	private String googleAppRedirectUrl;
}
