package com.oauth.security.repository.mongo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.oauth.security.entity.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

	Page<Product> findProductByIsActive(boolean b, Pageable pageable);

	Product findProductByNameAndIsActive(String name, boolean b);

}
