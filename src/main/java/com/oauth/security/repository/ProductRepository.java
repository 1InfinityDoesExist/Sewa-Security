package com.oauth.security.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.oauth.security.entity.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

	Product findProductByName(String product);

}
