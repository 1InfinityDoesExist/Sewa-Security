package com.oauth.security.service;

import org.springframework.stereotype.Component;

import com.oauth.security.model.request.ProductRequest;
import com.oauth.security.model.response.ProductResponse;

@Component
public interface ProductService {

	ProductResponse registerProductUsingPOST(ProductRequest productRequest);

}
