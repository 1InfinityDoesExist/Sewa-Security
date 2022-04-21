package com.oauth.security.service;

import org.springframework.stereotype.Component;

import com.oauth.security.model.request.RegistrationRequest;
import com.oauth.security.model.response.RegistrationResponse;

@Component
public interface CustomerService {

	RegistrationResponse registerEmailUsingPOST(RegistrationRequest registration);

}
