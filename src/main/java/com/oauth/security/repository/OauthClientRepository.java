package com.oauth.security.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.oauth.security.entity.OauthClients;

@Repository
public interface OauthClientRepository extends MongoRepository<OauthClients, String> {

	OauthClients findOauthClientsByClientId(String clientId);

}
