package com.oauth.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.oauth.security.entity.Product;
import com.oauth.security.model.request.ProductCreateRequest;
import com.oauth.security.model.response.ProductResponse;
import com.oauth.security.repository.mongo.ProductRepository;
import com.oauth.security.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public ProductResponse registerProductUsingPOST(ProductCreateRequest productCreateRequest) {
		log.info("-----ProductServiceImpl Class, registerProductUsingPOST method.-----");

		Product product = productRepository.findProductByNameAndIsActive(productCreateRequest.getName(), true);
		if (ObjectUtils.isEmpty(product)) {
			product = new Product();
			product.setActive(true);
			product.setName(productCreateRequest.getName());
			product.setRoles(productCreateRequest.getRoles());
			productRepository.save(product);

			ProductResponse response = new ProductResponse();
			response.setId(product.getId());
			response.setMsg("Success");
			return response;
		} else {
			throw new RuntimeException(
					String.format("Product with name : %s already exist", productCreateRequest.getName()));
		}
	}

	@Override
	public Product retrieveProductUsingGET(String name) {
		log.info("-----ProductServiceImpl Class, retrieveProductUsingGET method.-----");
		Product product = productRepository.findProductByNameAndIsActive(name, true);
		if (ObjectUtils.isEmpty(product)) {
			throw new RuntimeException(String.format("Product with name %s does not exit", name));
		}
		return product;
	}

	@Override
	public Page<Product> retrieveAllProductsUsingGET(Pageable pageable) {
		log.info("-----ProductServiceImpl Class, retrieveAllProductsUsingGET method.-----");

		return productRepository.findProductByIsActive(true, pageable);
	}

	@Override
	public void deleteProductUsingDELETE(String name) {
		log.info("-----ProductServiceImpl Class, deleteProductUsingDELETE method.-----");
		Product product = retrieveProductUsingGET(name);
		product.setActive(false);
		productRepository.save(product);
		return;
	}
}
