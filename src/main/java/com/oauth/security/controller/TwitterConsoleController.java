package com.oauth.security.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oauth.security.entity.TwitterConsole;
import com.oauth.security.model.request.TwitterConsoleCreateRequest;
import com.oauth.security.model.request.TwitterConsoleUpdateRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaPKMSTServerCodegen", date = "2021-06-07T09:54:05.519Z")

@Api(value = "TwitterConsoleController", description = "The TwitterConsoleController API")
public interface TwitterConsoleController {

	@ApiOperation(value = "Api to register twitter console credentials", notes = "", response = TwitterConsole.class, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created", response = TwitterConsole.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found") })
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", paramType = "header", required = true) })
	@RequestMapping(value = "/v1.0/twitter-console", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<TwitterConsole> registerTwitterConsoleUsingPOST(
			@ApiParam(value = "twitterConsoleCreateRequest", required = true) @RequestBody TwitterConsoleCreateRequest twitterConsoleCreateRequest)
			throws Exception;

	@ApiOperation(value = "Api to retrieve twitter console credentials", notes = "", response = TwitterConsole.class, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = TwitterConsole.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found") })
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", paramType = "header", required = true) })
	@RequestMapping(value = "/v1.0/twitter-console/{productId}", produces = {
			"application/json" }, method = RequestMethod.GET)
	public ResponseEntity<TwitterConsole> retrieveRegisteredTwitterConsoleUsingGET(
			@ApiParam(value = "productId", required = true) @PathVariable(value = "productId", required = true) String productId)
			throws Exception;

	@ApiOperation(value = "Api to remove twitter console credentials", notes = "", response = Void.class, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 204, message = "No Content", response = Void.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found") })
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", paramType = "header", required = true) })
	@RequestMapping(value = "/v1.0/twitter-console/{productId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> unregisterTwitterConsoleUingDELETE(
			@ApiParam(value = "productId", required = true) @PathVariable(value = "productId", required = true) String productId)
			throws Exception;

	@ApiOperation(value = "Api to update the twitter console credentials", notes = "", response = TwitterConsole.class, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = TwitterConsole.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found") })
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", paramType = "header", required = true) })
	@RequestMapping(value = "/v1.0/twitter-console/{productId}", produces = {
			"application/json" }, method = RequestMethod.PUT)
	public ResponseEntity<TwitterConsole> updateRegisteredTwitterConsoleUsingPUT(
			@ApiParam(value = "twitterConsoleUpdateRequest", required = true) @RequestBody TwitterConsoleUpdateRequest twitterConsoleUpdateRequest,
			@ApiParam(value = "productId", required = true) @PathVariable(value = "productId", required = true) String productId)
			throws Exception;

	@ApiOperation(value = "Api to retrieve all the twitter console credentials", notes = "", response = TwitterConsole.class, tags = {})
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = TwitterConsole.class, responseContainer = "List"),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found") })
	@ApiImplicitParams({ @ApiImplicitParam(name = "token", paramType = "header", required = true) })
	@RequestMapping(value = "/v1.0/twitter-console", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<TwitterConsole>> getAllTwitterConsole();
}
