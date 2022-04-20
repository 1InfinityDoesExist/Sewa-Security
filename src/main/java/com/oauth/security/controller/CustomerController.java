package com.oauth.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oauth.security.model.request.CustomerRequest;
import com.oauth.security.model.response.CustomerResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaPKMSTServerCodegen", date = "2021-06-07T09:54:05.519Z")

@Api(value = "CustomerController", description = "The CustomerController API")
public interface CustomerController {

	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created", response = ModelMap.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found") })
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", paramType = "header", required = true) })
	@RequestMapping(value = "/v1.0/customers", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	@PreAuthorize("hasRole('SUPER_ADMIN')")
	public ResponseEntity<CustomerResponse> registerCustomerUsingPOST(
			@ApiParam(value = "CustomerReqeust", required = true) @RequestBody CustomerRequest CustomerReqeust)
			throws Exception;
}
