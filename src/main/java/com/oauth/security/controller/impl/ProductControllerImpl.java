package com.oauth.security.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.oauth.security.controller.ProductController;
import com.oauth.security.entity.Product;
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
	public ResponseEntity<ProductResponse> createProductUsingPOST(ProductRequest productRequest) {
		log.info("-----ProductControllerImpl Class registerProductUsingPOST method-----");

		return ResponseEntity.status(HttpStatus.CREATED).body(productService.registerProductUsingPOST(productRequest));

	}

	@Override
	public ResponseEntity<Product> retrieveProductUsingGET(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<List<Product>> retrieveAllProductsUsingGET(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<String> deleteProductUsingDELETE(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ProductResponse> updateProductUsingPUT(ProductRequest updateProductRequest, String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
