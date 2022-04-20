package com.oauth.security.controller.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.oauth.security.controller.CustomerController;
import com.oauth.security.model.request.CustomerRequest;
import com.oauth.security.model.response.CustomerResponse;

@RestController
public class CustomerControllerImpl implements CustomerController {

	@Override
	public ResponseEntity<CustomerResponse> registerCustomerUsingPOST(CustomerRequest CustomerReqeust)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
