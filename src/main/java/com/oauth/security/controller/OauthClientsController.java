package com.oauth.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oauth.security.model.request.OauthClientsRequest;
import com.oauth.security.model.response.OauthClientsResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaPKMSTServerCodegen", date = "2021-06-07T09:54:05.519Z")

@Api(value = "ClientRegistrationController", description = "The ClientRegistrationController API")
public interface OauthClientsController {

	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created", response = ModelMap.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found") })
	@RequestMapping(value = "/v1.0/client-register", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<OauthClientsResponse> registerClientUsingPOST(
			@ApiParam(value = "clientRequest", required = true) @RequestBody OauthClientsRequest clientRequest)
			throws Exception;
}
