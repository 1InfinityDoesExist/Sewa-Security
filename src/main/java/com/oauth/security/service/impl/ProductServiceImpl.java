package com.oauth.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.oauth.security.entity.Product;
import com.oauth.security.model.request.ProductRequest;
import com.oauth.security.model.response.ProductResponse;
import com.oauth.security.repository.ProductRepository;
import com.oauth.security.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public ProductResponse registerProductUsingPOST(ProductRequest productRequest) {
		log.info("-----ProductServiceImpl Class, registerProductUsingPOST method.-----");

		Product product = productRepository.findProductByName(productRequest.getName());
		if (ObjectUtils.isEmpty(product)) {
			product = new Product();
			product.setActive(true);
			product.setName(productRequest.getName());
			product.setRoles(productRequest.getRoles());
			productRepository.save(product);

			ProductResponse response = new ProductResponse();
			response.setId(product.getId());
			response.setMsg("Success");
			return response;
		} else {
			throw new RuntimeException(String.format("Product with name : %s already exist", productRequest.getName()));
		}
	}
}
