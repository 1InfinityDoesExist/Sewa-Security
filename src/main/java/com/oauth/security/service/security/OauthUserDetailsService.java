package com.oauth.security.service.security;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.oauth.security.entity.Customer;
import com.oauth.security.repository.mongo.CustomerRepository;
import com.oauth.security.utils.CommonConstants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("oauth_user")
public class OauthUserDetailsService implements UserDetailsService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("-----Load user by username-----");

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();

		String product = request.getParameter(CommonConstants.PRODUCT);

		if (username.matches(CommonConstants.EMAILREGEX)) {
			Customer customer = customerRepository.findCustomerByEmailAndProduct(username, product);
			if (ObjectUtils.isEmpty(customer)) {
				throw new RuntimeException("Customer does not exit.");
			}
			return new User(username, customer.getPassword(), getCustomerRoles(customer));
		}
		throw new RuntimeException("Yet to be implemented");
	}

	private List<SimpleGrantedAuthority> getCustomerRoles(Customer customer) {
		List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
		authorityList.add(new SimpleGrantedAuthority("ROLE_" + customer.getRoles()));
		return authorityList;
	}

}
