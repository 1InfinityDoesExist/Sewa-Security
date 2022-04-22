package com.oauth.security.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.oauth.security.entity.OtpDetails;

@Repository
public interface OtpDetailsRepository extends MongoRepository<OtpDetails, String> {

	OtpDetails findOtpDetailsByEmailAndProduct(String email, String product);

}
