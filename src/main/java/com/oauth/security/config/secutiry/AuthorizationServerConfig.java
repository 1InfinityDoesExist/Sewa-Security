package com.oauth.security.config.secutiry;

import java.util.Arrays;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import com.oauth.security.service.security.OauthClientDetailsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Resource(name = "oauth_client")
	private OauthClientDetailsService oauthClientDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		log.info("-----configure(AuthorizationServerSecurityConfigurer security) -----");

		security.checkTokenAccess("isAuthenticated()").tokenKeyAccess("isAuthenticated()")
				.passwordEncoder(passwordEncoder);
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		log.info("-----configure(ClientDetailsServiceConfigurer clients)-----");
		clients.withClientDetails(oauthClientDetailsService);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		log.info("-----configure(AuthorizationServerEndpointsConfigurer endpoints) -----");

		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), jwtAccessTokenConverter()));
		endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
				.accessTokenConverter(jwtAccessTokenConverter()).tokenEnhancer(tokenEnhancerChain);
	}

	@Bean
	public TokenEnhancer tokenEnhancer() {
		log.info("----Custome token enhancer chain-----");
		return new CustomTokenEnhancer();
	}

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(jwtAccessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		KeyStoreKeyFactory keyFactory = new KeyStoreKeyFactory(new ClassPathResource("ssia.jks"),
				"ssia123".toCharArray());
		jwtAccessTokenConverter.setKeyPair(keyFactory.getKeyPair("ssia"));
		return jwtAccessTokenConverter;
	}
}
