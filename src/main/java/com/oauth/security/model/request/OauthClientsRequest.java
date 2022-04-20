package com.oauth.security.model.request;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OauthClientsRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String clientSecret;
	private Set<String> registeredRedirectUri;
	private Set<String> authorities;
	private Integer accessTokenValiditySeconds;
	private Integer refreshTokenValiditySeconds;
	private Map<String, Object> additionalInformation;

	@NotEmpty(message = "Product filed must not be null or empty")
	private String product;

}
