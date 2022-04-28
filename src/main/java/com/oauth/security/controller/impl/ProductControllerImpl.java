package com.oauth.security.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.oauth.security.controller.ProductController;
import com.oauth.security.model.request.ProductRequest;
import com.oauth.security.model.response.ProductResponse;
import com.oauth.security.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ProductControllerImpl implements ProductController {

	@Autowired
	private ProductService productService;

	/**
	 * 
	 */
	@Override
	public ResponseEntity<ProductResponse> registerProductUsingPOST(ProductRequest productRequest) {
		log.info("-----ProductControllerImpl Class registerProductUsingPOST method-----");

		return ResponseEntity.status(HttpStatus.CREATED).body(productService.registerProductUsingPOST(productRequest));

	}

}
