package com.oauth.security.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.oauth.security.entity.TwitterConsole;

@Repository
public interface TwitterConsoleRepository extends MongoRepository<TwitterConsole, String> {

	TwitterConsole findTwitterConsoleByProductId(String productId);

}
