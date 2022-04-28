package com.oauth.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oauth.security.model.request.ProductRequest;
import com.oauth.security.model.response.ProductResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaPKMSTServerCodegen", date = "2021-06-07T09:54:05.519Z")
@Api(value = "ProductController", description = "Product Crud Apis.")
public interface ProductController {

	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created", response = ProductResponse.class),
			@ApiResponse(code = 401, message = "Unauthroized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 200, message = "OK") })
	@RequestMapping(value = "/v1.0/products", consumes = { "application/json" }, produces = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<ProductResponse> registerProductUsingPOST(@RequestBody ProductRequest productRequest);

}
