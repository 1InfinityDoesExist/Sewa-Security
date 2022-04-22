package com.oauth.security.utils;

import org.springframework.stereotype.Component;

@Component
public class CommonConstants {

	public static final String MOBILEREGEX = "[0-9]+";
	public static final String EMAILREGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	public static final String PRODUCT = "product";
	public static final String EMAIL = "email";
	public static final String MOBILE = "mobile";
	public static final String USERNAME = "username";

}
