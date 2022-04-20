package com.oauth.security.service.security;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("oauth_client")
public class OauthClientDetailsService implements ClientDetailsService {

	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {

		return null;
	}

}
