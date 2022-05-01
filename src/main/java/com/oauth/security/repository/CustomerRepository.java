package com.oauth.security.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.oauth.security.entity.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

	Customer findCustomerByEmailAndProduct(String email, String product);

	Customer findCustomerByUserNameAndProduct(String userName, String product);

	Customer findCustomerByIdAndIsActive(String id, boolean b);

	Page<Customer> findCustomerByIsActive(boolean b, Pageable pageable);

}
