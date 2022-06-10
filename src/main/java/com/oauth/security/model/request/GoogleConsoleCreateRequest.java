package com.oauth.security.model.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.ToString;

@lombok.Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleConsoleCreateRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1554086232725035093L;
	@NotBlank(message = "ProductId filed must not be null or empty")
	private String productId;
	@NotBlank(message = "GoogleAppId filed must not be null or empty")
	private String googleAppId;
	@NotBlank(message = "GoogleAppSecret filed must not be null or empty")
	private String googleAppSecret;

	private String googleAppRedirectUrl;

}
