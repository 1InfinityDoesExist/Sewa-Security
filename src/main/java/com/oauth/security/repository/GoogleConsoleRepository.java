package com.oauth.security.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.oauth.security.entity.GoogleConsole;

@Repository
public interface GoogleConsoleRepository extends MongoRepository<GoogleConsole, String> {

	GoogleConsole findGoogleConsoleByProductId(String productId);

}
