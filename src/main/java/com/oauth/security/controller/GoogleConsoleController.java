package com.oauth.security.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oauth.security.entity.GoogleConsole;
import com.oauth.security.model.request.GoogleConsoleCreateRequest;
import com.oauth.security.model.request.GoogleConsoleUpdateRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaPKMSTServerCodegen", date = "2021-06-07T09:54:05.519Z")

@Api(value = "GoogleConsoleController", description = "The GoogleConsoleController API")
public interface GoogleConsoleController {

	@ApiOperation(value = "Api to register google console credentials", notes = "", response = GoogleConsole.class, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created", response = GoogleConsole.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found") })
	@RequestMapping(path = "/v1.0/google-console", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<GoogleConsole> registerGoogleConsoleUsingPOST(
			@ApiParam(value = "googleConsoleCreateRequest", required = true) @RequestBody GoogleConsoleCreateRequest googleConsoleCreateRequest)
			throws Exception;

	@ApiOperation(value = "Api to retrieve google console credentials", notes = "", response = GoogleConsole.class, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = GoogleConsole.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found") })
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", paramType = "header", required = true) })
	@RequestMapping(value = "/v1.0/google-console/{productId}", produces = {
			"application/json" }, method = RequestMethod.GET)
	public ResponseEntity<GoogleConsole> retrieveRegisteredGoogleConsoleUsingGET(
			@ApiParam(value = "productId", required = true) @PathVariable(value = "productId", required = true) String productId)
			throws Exception;

	@ApiOperation(value = "Api to remove google console credentials", notes = "", response = Void.class, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 204, message = "No Content", response = Void.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found") })
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", paramType = "header", required = true) })
	@RequestMapping(value = "/v1.0/google-console/{productId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> unregisterGoogleConsoleUingDELETE(
			@ApiParam(value = "productId", required = true) @PathVariable(value = "productId", required = true) String productId)
			throws Exception;

	@ApiOperation(value = "Api to update the google console credentials", notes = "", response = GoogleConsole.class, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = GoogleConsole.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found") })
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", paramType = "header", required = true) })
	@RequestMapping(value = "/v1.0/google-console/{productId}", produces = {
			"application/json" }, method = RequestMethod.PUT)
	public ResponseEntity<GoogleConsole> updateRegisteredGoogleConsoleUsingPUT(
			@ApiParam(value = "googleConsoleUpdateRequest", required = true) @RequestBody GoogleConsoleUpdateRequest googleConsoleUpdateRequest,
			@ApiParam(value = "productId", required = true) @PathVariable(value = "productId", required = true) String productId)
			throws Exception;

	@ApiOperation(value = "Api to retrieve all the google console credentials", notes = "", response = GoogleConsole.class, tags = {})
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = GoogleConsole.class, responseContainer = "List"),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found") })
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", paramType = "header", required = true) })
	@RequestMapping(value = "/v1.0/google-console", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<GoogleConsole>> getAllGoogleConsole();
}