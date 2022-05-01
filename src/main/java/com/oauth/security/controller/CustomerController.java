package com.oauth.security.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oauth.security.entity.Customer;
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
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 200, message = "OK") })
	@RequestMapping(value = "/v1.0/customers/register-email", consumes = { "application/json" }, produces = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<RegistrationResponse> registerEmailUsingPOST(
			@Valid @RequestBody RegistrationRequest registration);

	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created", response = OTPVerificationResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 200, message = "OK") })
	@RequestMapping(value = "/v1.0/customers/verify-email-otp", consumes = { "application/json" }, produces = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<OTPVerificationResponse> verifyEmailOTPUsingPOST(
			@Valid @RequestBody OTPVerificationRequest oTPVerificationRequest);

	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created", response = RegistrationResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 200, message = "OK") })
	@RequestMapping(value = "/v1.0/customers/register-mobile", consumes = { "application/json" }, produces = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<RegistrationResponse> registerMobileUsingPOST(
			@Valid @RequestBody RegistrationRequest registration);

	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created", response = OTPVerificationResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 200, message = "OK") })
	@RequestMapping(value = "/v1.0/customers/verify-mobile-otp", consumes = { "application/json" }, produces = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<OTPVerificationResponse> verifyMobileOTPUsingPOST(
			@Valid @RequestBody OTPVerificationRequest oTPVerificationRequest);

	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created", response = CustomerResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 200, message = "OK") })
	@RequestMapping(value = "/v1.0/customers", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<CustomerResponse> createCustomerUsingPOST(
			@ApiParam(value = "CustomerReqeust", required = true) @Valid @RequestBody CustomerRequest CustomerReqeust)
			throws Exception;

	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 200, message = "OK", response = Customer.class) })
	@RequestMapping(value = "/v1.0/customers/{id}", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.GET)
	public ResponseEntity<Customer> retrieveCustomerUsingGET(
			@ApiParam(value = "id", required = true) @PathVariable(value = "id", required = true) String id)
			throws Exception;

	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 200, message = "OK", response = List.class) })
	@RequestMapping(value = "/v1.0/customers/{id}", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<Customer>> retrieveAllCustomerUsingGET(
			@PageableDefault(page = 0, size = 10) Pageable pageable) throws Exception;

	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 200, message = "OK", response = String.class) })
	@RequestMapping(value = "/v1.0/customers/{id}", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteCustomerUsingDELETE(
			@ApiParam(value = "id", required = true) @PathVariable(value = "id", required = true) String id)
			throws Exception;

	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 200, message = "OK", response = CustomerResponse.class) })
	@RequestMapping(value = "/v1.0/customers/{id}", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.PUT)
	public ResponseEntity<CustomerResponse> updateCustomerUsingPUT(
			@ApiParam(value = "customerUpdateRequest", required = true) @Valid @RequestBody CustomerRequest customerUpdateRequest,
			@ApiParam(value = "id", required = true) @PathVariable(value = "id", required = true) String id)
			throws Exception;
}
