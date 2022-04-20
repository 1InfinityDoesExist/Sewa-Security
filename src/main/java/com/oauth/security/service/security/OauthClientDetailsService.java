package com.oauth.security.service.security;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import com.oauth.security.entity.OauthClients;
import com.oauth.security.repository.OauthClientRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("oauth_client")
public class OauthClientDetailsService implements ClientDetailsService {

	@Autowired
	private OauthClientRepository oauthClientRepository;

	@SuppressWarnings("serial")
	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		log.info("----Load client by clientId : {}", clientId);

		OauthClients oauthClients = oauthClientRepository.findOauthClientsByClientId(clientId);
		log.info("-----ClientDetails from DB : {}", oauthClients);
		return new ClientDetails() {

			@Override
			public boolean isSecretRequired() {
				// TODO Auto-generated method stub
				return oauthClients.isSecretRequired();
			}

			@Override
			public boolean isScoped() {
				// TODO Auto-generated method stub
				return oauthClients.isScoped();
			}

			@Override
			public boolean isAutoApprove(String scope) {
				// TODO Auto-generated method stub
				return oauthClients.isAutoApprove();
			}

			@Override
			public Set<String> getScope() {
				// TODO Auto-generated method stub
				return oauthClients.getScope();
			}

			@Override
			public Set<String> getResourceIds() {
				// TODO Auto-generated method stub
				return oauthClients.getResourceIds();
			}

			@Override
			public Set<String> getRegisteredRedirectUri() {
				// TODO Auto-generated method stub
				return oauthClients.getRegisteredRedirectUri();
			}

			@Override
			public Integer getRefreshTokenValiditySeconds() {
				// TODO Auto-generated method stub
				return oauthClients.getRefreshTokenValiditySeconds();
			}

			@Override
			public String getClientSecret() {
				// TODO Auto-generated method stub
				return oauthClients.getClientSecret();
			}

			@Override
			public String getClientId() {
				// TODO Auto-generated method stub
				return oauthClients.getClientId();
			}

			@Override
			public Set<String> getAuthorizedGrantTypes() {
				// TODO Auto-generated method stub
				return oauthClients.getAuthorizedGrantTypes();
			}

			@Override
			public Collection<GrantedAuthority> getAuthorities() {
				// TODO Auto-generated method stub
				return oauthClients.getAuthorities();
			}

			@Override
			public Map<String, Object> getAdditionalInformation() {
				// TODO Auto-generated method stub
				return oauthClients.getAdditionalInformation();
			}

			@Override
			public Integer getAccessTokenValiditySeconds() {
				// TODO Auto-generated method stub
				return oauthClients.getAccessTokenValiditySeconds();
			}
		};
	}

}
