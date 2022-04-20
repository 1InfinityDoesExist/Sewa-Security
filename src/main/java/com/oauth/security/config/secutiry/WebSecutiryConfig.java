package com.oauth.security.config.secutiry;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity(debug = false)
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecutiryConfig extends WebSecurityConfigurerAdapter {

//	@Resource(name = "oauth_user")
//	private OauthUserDetailsService oauthUserDetailsService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		log.info("----passwordEncoder()-----");
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		log.info("----userDetailsService()-----");
		InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
		UserDetails userDetails = User.withUsername("patel").password(passwordEncoder().encode("patel"))
				.authorities("read", "write").roles("ADMIN", "SUPER_ADMIN").build();
		inMemoryUserDetailsManager.createUser(userDetails);
		return inMemoryUserDetailsManager;
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		log.info("----authenticationManagerBean()-----");
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		log.info("----configure(HttpSecurity http()-----");
		http.csrf().disable();
		http.httpBasic();
		http.authorizeRequests().anyRequest().permitAll();

	}

}
