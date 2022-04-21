package com.oauth.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oauth.security.model.request.CustomerRequest;
import com.oauth.security.model.request.OTPVerificationRequest;
import com.oauth.security.model.request.RegistrationRequest;
import com.oauth.security.model.response.CustomerResponse;
import com.oauth.security.model.response.OTPVerificationResponse;
import com.oauth.security.model.response.RegistrationResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaPKMSTServerCodegen", date = "2021-06-07T09:54:05.519Z")

@Api(value = "CustomerController", description = "The CustomerController API")
public interface CustomerController {

	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created", response = RegistrationResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found") })
	@RequestMapping(value = "/v1.0/customers/register-email", consumes = { "application/json" }, produces = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<RegistrationResponse> registerEmailUsingPOST(@RequestBody RegistrationRequest registration);

	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created", response = OTPVerificationResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found") })
	@RequestMapping(value = "/v1.0/customers/verify-email-otp", consumes = { "application/json" }, produces = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<OTPVerificationResponse> verifyEmailOTPUsingPOST(
			@RequestBody OTPVerificationRequest oTPVerificationRequest);

	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created", response = RegistrationResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found") })
	@RequestMapping(value = "/v1.0/customers/register-mobile", consumes = { "application/json" }, produces = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<RegistrationResponse> registerMobileUsingPOST(@RequestBody RegistrationRequest registration);

	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created", response = OTPVerificationResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found") })
	@RequestMapping(value = "/v1.0/customers/verify-mobile-otp", consumes = { "application/json" }, produces = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<OTPVerificationResponse> verifyMobileOTPUsingPOST(
			@RequestBody OTPVerificationRequest oTPVerificationRequest);

	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created", response = CustomerResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found") })
	@RequestMapping(value = "/v1.0/customers", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<CustomerResponse> registerCustomerUsingPOST(
			@ApiParam(value = "CustomerReqeust", required = true) @RequestBody CustomerRequest CustomerReqeust)
			throws Exception;
}
