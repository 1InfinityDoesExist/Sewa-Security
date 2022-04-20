package com.oauth.security.service.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.oauth.security.entity.OauthClients;
import com.oauth.security.model.request.OauthClientsRequest;
import com.oauth.security.model.response.OauthClientsResponse;
import com.oauth.security.repository.OauthClientRepository;
import com.oauth.security.service.OauthClientsServcie;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OauthClientsServiceImpl implements OauthClientsServcie {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private OauthClientRepository oauthClientRepository;

	/**
	 * 
	 */
	@Override
	public OauthClientsResponse registerClientUsingPOST(OauthClientsRequest clientRequest) {
		log.info("-----Client Request : {}", clientRequest);

		OauthClients oauthClients = OauthClients.builder()
				.clientId(UUID.nameUUIDFromBytes((UUID.randomUUID() + new Date().toString()).getBytes()).toString())
				.password(clientRequest.getClientSecret()).scope(new HashSet<String>())
				.product(clientRequest.getProduct()).isActive(true)
				.clientSecret(passwordEncoder.encode(clientRequest.getClientSecret()))
				.accessTokenValiditySeconds(clientRequest.getAccessTokenValiditySeconds())
				.refreshTokenValiditySeconds(clientRequest.getRefreshTokenValiditySeconds())
				.additionalInformation(clientRequest.getAdditionalInformation())
				.authorizedGrantTypes(
						new HashSet<>(Arrays.asList("authorization_code", "refresh_token", "implicit", "password")))
				.isAutoApprove(true).isScoped(true).isSecretRequired(true)
				.scope(new HashSet<String>(Arrays.asList("all")))
				.authorities(setGrantedAuthorities(clientRequest.getAuthorities())).build();
		oauthClientRepository.save(oauthClients);

		log.info("-----OauthClient Id  : {}", oauthClients.getId());

		return OauthClientsResponse.builder().clientId(oauthClients.getClientId())
				.clientSecret(oauthClients.getClientSecret()).id(oauthClients.getId()).build();
	}

	/**
	 * 
	 * @param authorizedGrantTypes
	 * @return
	 */
	private Collection<GrantedAuthority> setGrantedAuthorities(Set<String> authorizedGrantTypes) {
		return authorizedGrantTypes.parallelStream().map(role -> new SimpleGrantedAuthority(role))
				.collect(Collectors.toList());
	}

}
