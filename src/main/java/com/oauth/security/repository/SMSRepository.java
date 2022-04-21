package com.oauth.security.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.oauth.security.model.SMSMessage;

@Repository
public interface SMSRepository extends MongoRepository<SMSMessage, String> {

	SMSMessage findSMSMessageBySid(String messageSid);

}
