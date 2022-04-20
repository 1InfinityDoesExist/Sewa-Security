package com.oauth.security.model.request;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

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

	private String clientId;
	private Set<String> resourceIds;
	private boolean isSecretRequired;
	private String clientSecret;
	private boolean isScoped;
	private Set<String> Scope;
	private Set<String> authorizedGrantTypes;
	private Set<String> registeredRedirectUri;
	private Collection<GrantedAuthority> authorities;
	private Integer accessTokenValiditySeconds;
	private Integer refreshTokenValiditySeconds;
	private boolean isAutoApprove;
	private Map<String, Object> additionalInformation;

}
