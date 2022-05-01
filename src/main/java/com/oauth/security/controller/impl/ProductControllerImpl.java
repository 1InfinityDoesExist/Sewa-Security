package com.oauth.security.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RestController;

import com.oauth.security.controller.ProductController;
import com.oauth.security.entity.Product;
import com.oauth.security.model.request.ProductCreateRequest;
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
	public ResponseEntity<ProductResponse> createProductUsingPOST(ProductCreateRequest productCreateRequest) {
		log.info("-----ProductControllerImpl Class registerProductUsingPOST method-----");

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(productService.registerProductUsingPOST(productCreateRequest));

	}

	@Override
	public ResponseEntity<Product> retrieveProductUsingGET(String name) {
		log.info("-----ProductControllerImpl Class retrieveProductUsingGET method-----");

		return ResponseEntity.status(HttpStatus.OK).body(productService.retrieveProductUsingGET(name));
	}

	@Override
	public ResponseEntity<Page<Product>> retrieveAllProductsUsingGET(Pageable pageable) {
		log.info("-----ProductControllerImpl Class retrieveAllProductsUsingGET method-----");
		return ResponseEntity.status(HttpStatus.OK).body(productService.retrieveAllProductsUsingGET(pageable));
	}

	@Override
	public ResponseEntity<ModelMap> deleteProductUsingDELETE(String name) {
		log.info("-----ProductControllerImpl Class deleteProductUsingDELETE method-----");

		productService.deleteProductUsingDELETE(name);
		return ResponseEntity.status(HttpStatus.OK).body(new ModelMap().addAttribute("msg", "success"));
	}

}
