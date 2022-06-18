package com.oauth.security.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.oauth.security.entity.FacebookConsole;

@Repository
public interface FacebookConsolerRepository extends MongoRepository<FacebookConsole, String> {

	FacebookConsole findFacebookConsoleByProductId(String productId);

}
