package com.oauth.security.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oauth.security.entity.Vendor;
import com.oauth.security.model.request.OTPVerificationRequest;
import com.oauth.security.model.request.RegistrationRequest;
import com.oauth.security.model.request.VendorRequest;
import com.oauth.security.model.response.OTPVerificationResponse;
import com.oauth.security.model.response.RegistrationResponse;
import com.oauth.security.model.response.VendorResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaPKMSTServerCodegen", date = "2021-06-07T09:54:05.519Z")

@Api(value = "VendorController", description = "The Vendor Controller API")
public interface VendorController {

	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created", response = RegistrationResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 200, message = "OK") })
	@RequestMapping(value = "/v1.0/vendors/register-email", consumes = { "application/json" }, produces = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<RegistrationResponse> registerEmailUsingPOST(@RequestBody RegistrationRequest registration);

	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created", response = OTPVerificationResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 200, message = "OK") })
	@RequestMapping(value = "/v1.0/vendors/verify-email-otp", consumes = { "application/json" }, produces = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<OTPVerificationResponse> verifyEmailOTPUsingPOST(
			@RequestBody OTPVerificationRequest oTPVerificationRequest);

	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created", response = RegistrationResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 200, message = "OK") })
	@RequestMapping(value = "/v1.0/vendors/register-mobile", consumes = { "application/json" }, produces = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<RegistrationResponse> registerMobileUsingPOST(@RequestBody RegistrationRequest registration);

	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created", response = OTPVerificationResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 200, message = "OK") })
	@RequestMapping(value = "/v1.0/vendors/verify-mobile-otp", consumes = { "application/json" }, produces = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<OTPVerificationResponse> verifyMobileOTPUsingPOST(
			@RequestBody OTPVerificationRequest oTPVerificationRequest);

	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created", response = VendorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 200, message = "OK") })
	@RequestMapping(value = "/v1.0/vendors", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<VendorResponse> createVendorsUsingPOST(
			@ApiParam(value = "vendorRequest", required = true) @RequestBody VendorRequest vendorRequest)
			throws Exception;

	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 200, message = "OK", response = Vendor.class) })
	@RequestMapping(value = "/v1.0/vendors/{id}", produces = {
			"application/json" }, consumes = {}, method = RequestMethod.GET)
	public ResponseEntity<Vendor> retrieveVendorUsingGET(@ApiParam(value = "id", required = true) String id)
			throws Exception;

	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 200, message = "OK", response = String.class) })
	@RequestMapping(value = "/v1.0/vendors/{id}", produces = {}, consumes = {}, method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteVendorsUsingDELETE(@ApiParam(value = "id", required = true) String id)
			throws Exception;

	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 200, message = "OK", response = List.class) })
	@RequestMapping(value = "/v1.0/vendors", produces = {
			"application/json" }, consumes = {}, method = RequestMethod.GET)
	public ResponseEntity<List<Vendor>> retrieveAllVendorsUsingGET(
			@PageableDefault(page = 0, size = 10) Pageable pageable) throws Exception;

	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 200, message = "OK", response = VendorResponse.class) })
	@RequestMapping(value = "/v1.0/vendors/{id}", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.PUT)
	public ResponseEntity<VendorResponse> updateVendorsUsingPUT(
			@ApiParam(value = "vendorRequest", required = true) @RequestBody VendorRequest vendorRequest,
			@ApiParam(value = "id", required = true) String id) throws Exception;

}
