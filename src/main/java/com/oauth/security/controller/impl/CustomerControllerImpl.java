package com.oauth.security.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.oauth.security.controller.CustomerController;
import com.oauth.security.model.request.CustomerRequest;
import com.oauth.security.model.request.OTPVerificationRequest;
import com.oauth.security.model.request.RegistrationRequest;
import com.oauth.security.model.response.CustomerResponse;
import com.oauth.security.model.response.OTPVerificationResponse;
import com.oauth.security.model.response.RegistrationResponse;
import com.oauth.security.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CustomerControllerImpl implements CustomerController {

	@Autowired
	private CustomerService customerService;

	@Override
	public ResponseEntity<CustomerResponse> registerCustomerUsingPOST(CustomerRequest CustomerReqeust)
			throws Exception {
		log.info("-----");
		return null;
	}

	@Override
	public ResponseEntity<RegistrationResponse> registerEmailUsingPOST(RegistrationRequest registration) {
		log.info("-----CustomerControllerImpl Class, registerEmailUsringPOST method-----");

		return ResponseEntity.status(HttpStatus.OK).body(customerService.registerEmailUsingPOST(registration));
	}

	@Override
	public ResponseEntity<OTPVerificationResponse> verifyEmailOTPUsingPOST(
			OTPVerificationRequest oTPVerificationRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<RegistrationResponse> registerMobileUsingPOST(RegistrationRequest registration) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<OTPVerificationResponse> verifyMobileOTPUsingPOST(
			OTPVerificationRequest oTPVerificationRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
