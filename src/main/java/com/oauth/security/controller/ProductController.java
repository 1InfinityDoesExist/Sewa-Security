package com.oauth.security.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oauth.security.entity.Product;
import com.oauth.security.model.request.ProductCreateRequest;
import com.oauth.security.model.request.ProductUpdateRequest;
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
	public ResponseEntity<ProductResponse> createProductUsingPOST(@RequestBody ProductCreateRequest productRequest);

	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 401, message = "Unauthroized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 200, message = "OK", response = Product.class) })
	@RequestMapping(value = "/v1.0/products/{name}", produces = {
			"application/json" }, consumes = {}, method = RequestMethod.GET)
	public ResponseEntity<Product> retrieveProductUsingGET(@PathVariable(value = "name", required = true) String name);

	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 401, message = "Unauthroized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 200, message = "OK", response = Page.class) })
	@RequestMapping(value = "/v1.0/products", produces = {
			"application/json" }, consumes = {}, method = RequestMethod.GET)
	public ResponseEntity<Page<Product>> retrieveAllProductsUsingGET(
			@PageableDefault(page = 0, size = 10) Pageable pageable);

	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 401, message = "Unauthroized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 200, message = "OK", response = String.class) })
	@RequestMapping(value = "/v1.0/products/{name}", produces = {}, consumes = {}, method = RequestMethod.DELETE)
	public ResponseEntity<ModelMap> deleteProductUsingDELETE(
			@PathVariable(value = "name", required = true) String name);

}
