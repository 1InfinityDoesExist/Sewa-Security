package com.oauth.security.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oauth.security.entity.FacebookConsole;
import com.oauth.security.model.request.FacebookConsoleCreateRequest;
import com.oauth.security.model.request.FacebookConsoleUpdateRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaPKMSTServerCodegen", date = "2021-06-07T09:54:05.519Z")

@Api(value = "FacebookConsoleController", description = "The FacebookConsoleController API")
public interface FacebookConsoleController {

	@ApiOperation(value = "Api to register facbook console credentials", notes = "", response = FacebookConsole.class, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created", response = FacebookConsole.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found") })
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", paramType = "header", required = true) })
	@RequestMapping(value = "/v1.0/facebook-console", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<FacebookConsole> registerFacebookConsoleUsingPOST(
			@ApiParam(value = "facebookConsoleCreateRequest", required = true) @RequestBody FacebookConsoleCreateRequest facebookConsoleCreateRequest)
			throws Exception;

	@ApiOperation(value = "Api to retrieve facbook console credentials", notes = "", response = FacebookConsole.class, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = FacebookConsole.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found") })
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", paramType = "header", required = true) })
	@RequestMapping(value = "/v1.0/facebook-console/{productId}", produces = {
			"application/json" }, method = RequestMethod.GET)
	public ResponseEntity<FacebookConsole> retrieveRegisteredFacebookConsoleUsingGET(
			@ApiParam(value = "productId", required = true) @PathVariable(value = "productId", required = true) String productId)
			throws Exception;

	@ApiOperation(value = "Api to retrieve all the facbook console credentials", notes = "", response = FacebookConsole.class, tags = {})
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = FacebookConsole.class, responseContainer = "List"),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found") })
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", paramType = "header", required = true) })
	@RequestMapping(value = "/v1.0/facebook-console", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<FacebookConsole>> getAllFacebookConsole();

	@ApiOperation(value = "Api to remove facbook console credentials", notes = "", response = Void.class, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 204, message = "No Content", response = Void.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found") })
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", paramType = "header", required = true) })
	@RequestMapping(value = "/v1.0/facebook-console/{productId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> unregisterFacebookConsoleUingDELETE(
			@ApiParam(value = "productId", required = true) @PathVariable(value = "productId", required = true) String productId)
			throws Exception;

	@ApiOperation(value = "Api to update the facbook console credentials", notes = "", response = FacebookConsole.class, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = FacebookConsole.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found") })
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", paramType = "header", required = true) })
	@RequestMapping(value = "/v1.0/facebook-console/{productId}", produces = {
			"application/json" }, method = RequestMethod.PUT)
	public ResponseEntity<FacebookConsole> updateRegisteredFacebookConsoleUsingPUT(
			@ApiParam(value = "facebookConsoleUpdateRequest", required = true) @RequestBody FacebookConsoleUpdateRequest facebookConsoleUpdateRequest,
			@ApiParam(value = "productId", required = true) @PathVariable(value = "productId", required = true) String productId)
			throws Exception;
}
