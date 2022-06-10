package com.oauth.security.model.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.ToString;

@lombok.Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FacebookConsoleUpdateRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -88930204002072397L;
	private String facebookAppId;
	private String facebookAppSecret;
	private String facebookAppRedirectUrl;
}
