package com.oauth.security.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.oauth.security.entity.Product;
import com.oauth.security.model.request.ProductCreateRequest;
import com.oauth.security.model.response.ProductResponse;

@Component
public interface ProductService {

	ProductResponse registerProductUsingPOST(ProductCreateRequest productCreateRequest);

	Product retrieveProductUsingGET(String name);

	Page<Product> retrieveAllProductsUsingGET(Pageable pageable);

	void deleteProductUsingDELETE(String name);

}
