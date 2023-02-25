package com.oauth.security.config.secutiry;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomTokenEnhancer implements TokenEnhancer {

	@Autowired
	private CacheManager cacheManager;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		log.info("-----enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication)-----");

		Map<String, Object> additionalInfo = new HashMap<String, Object>();
		additionalInfo.put("test", "test");

		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);

		System.out.println("jti " + accessToken);

		Cache cache = cacheManager.getCache("blacklist");
		cache.putIfAbsent(accessToken, true);

		return accessToken;

	}

}
