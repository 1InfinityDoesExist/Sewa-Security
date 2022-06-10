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
public class FacebookConsoleCreateRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1152924926394736709L;
	@NotBlank(message = "ProductId filed must not be null or empty")
	private String productId;
	@NotBlank(message = "FacebookAppId filed must not be null or empty")
	private String facebookAppId;
	@NotBlank(message = "FacebookAppSecret filed must not be null or empty")
	private String facebookAppSecret;

	private String facebookAppRedirectUrl;

}
