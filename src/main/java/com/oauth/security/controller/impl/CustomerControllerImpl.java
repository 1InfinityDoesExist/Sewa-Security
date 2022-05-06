package com.oauth.security.controller.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RestController;

import com.oauth.security.controller.CustomerController;
import com.oauth.security.entity.Customer;
import com.oauth.security.model.request.CustomerCreateRequest;
import com.oauth.security.model.request.CustomerUpdateRequest;
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
	public ResponseEntity<CustomerResponse> createCustomerUsingPOST(CustomerCreateRequest customerCreateRequest)
			throws Exception {
		log.info("-----CustomerControllerImpl Class, registerCustomerUsingPOST method-----");

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(customerService.registerCustomerUsingPOST(customerCreateRequest));
	}

	@Override
	public ResponseEntity<RegistrationResponse> registerEmailUsingPOST(RegistrationRequest registration) {
		log.info("-----CustomerControllerImpl Class, registerEmailUsringPOST method-----");

		return ResponseEntity.status(HttpStatus.OK).body(customerService.registerEmailUsingPOST(registration));
	}

	@Override
	public ResponseEntity<OTPVerificationResponse> verifyEmailOTPUsingPOST(
			OTPVerificationRequest otpVerificationRequest) {
		log.info("-----CustomerControllerImpl Class, customer email verification-----");
		return ResponseEntity.status(HttpStatus.OK)
				.body(customerService.verifyConsumerEmailUsingPOST(otpVerificationRequest));
	}

	@Override
	public ResponseEntity<RegistrationResponse> updateMobileUsingPOST(RegistrationRequest registration) {

		log.info("----Updating mobile number for user : {}", registration.getEmail());

		return ResponseEntity.status(HttpStatus.OK).body(customerService.updateMobileUsingPOST(registration));
	}

	@Override
	public ResponseEntity<OTPVerificationResponse> verifyMobileOTPUsingPOST(
			OTPVerificationRequest oTPVerificationRequest) {

		log.info("-----CustomerControllerImpl Class, verifyMobileOTPUsingPOST method.-----");
		return ResponseEntity.status(HttpStatus.OK)
				.body(customerService.verifyMobileOTPUsingPOST(oTPVerificationRequest));
	}

	@Override
	public ResponseEntity<Customer> retrieveCustomerUsingGET(String id) throws Exception {
		log.info("-----CustomerControllerImpl Class, retrieveCustomerUsingGET method.-----");

		return ResponseEntity.status(HttpStatus.OK).body(customerService.retrieveCustomerUsingGET(id));
	}

	@Override
	public ResponseEntity<Page<Customer>> retrieveAllCustomerUsingGET(Pageable pageable) throws Exception {
		log.info("-----CustomerControllerImpl Class, retrieveAllCustomerUsingGET method.-----");

		return ResponseEntity.status(HttpStatus.OK).body(customerService.retrieveAllCustomerUsingGET(pageable));
	}

	@Override
	public ResponseEntity<ModelMap> deleteCustomerUsingDELETE(String id) throws Exception {
		log.info("-----CustomerControllerImpl Class, deleteCustomerUsingDELETE method.-----");
		customerService.deleteCustomerUsingDELETE(id);
		return ResponseEntity.status(HttpStatus.OK).body(new ModelMap().addAttribute("msg", "success"));
	}

	@Override
	public ResponseEntity<CustomerResponse> updateCustomerUsingPUT(@Valid CustomerUpdateRequest customerUpdateRequest,
			String id) throws Exception {
		log.info("-----CustomerControllerImpl Class, updateCustomerUsingPUT method.-----");

		return ResponseEntity.status(HttpStatus.OK)
				.body(customerService.updateCustomerUsingPUT(id, customerUpdateRequest));
	}

}
