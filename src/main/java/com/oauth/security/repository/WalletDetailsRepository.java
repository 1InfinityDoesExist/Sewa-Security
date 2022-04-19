package com.oauth.security.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.oauth.security.entity.WalletDetails;

@Repository
public interface WalletDetailsRepository extends MongoRepository<WalletDetails, String> {

}
