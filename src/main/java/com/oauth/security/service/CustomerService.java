package com.oauth.security.service;

import javax.validation.Valid;

import org.json.simple.parser.ParseException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oauth.security.entity.Customer;
import com.oauth.security.model.request.CustomerCreateRequest;
import com.oauth.security.model.request.CustomerUpdateRequest;
import com.oauth.security.model.request.OTPVerificationRequest;
import com.oauth.security.model.request.RegistrationRequest;
import com.oauth.security.model.response.CustomerResponse;
import com.oauth.security.model.response.OTPVerificationResponse;
import com.oauth.security.model.response.RegistrationResponse;

@Component
public interface CustomerService {

	RegistrationResponse registerEmailUsingPOST(RegistrationRequest registration);

	OTPVerificationResponse verifyConsumerEmailUsingPOST(OTPVerificationRequest otpVerificationRequest);

	CustomerResponse registerCustomerUsingPOST(CustomerCreateRequest customerCreateRequest);

	Customer retrieveCustomerUsingGET(String id);

	Page<Customer> retrieveAllCustomerUsingGET(Pageable pageable);

	void deleteCustomerUsingDELETE(String id);

	CustomerResponse updateCustomerUsingPUT(String id, @Valid CustomerUpdateRequest customerUpdateRequest)
			throws JsonProcessingException, ParseException;

	RegistrationResponse updateMobileUsingPOST(RegistrationRequest registration);

	OTPVerificationResponse verifyMobileOTPUsingPOST(OTPVerificationRequest oTPVerificationRequest);

}
