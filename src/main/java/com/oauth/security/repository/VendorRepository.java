package com.oauth.security.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.oauth.security.entity.Vendor;

@Repository
public interface VendorRepository extends MongoRepository<Vendor, String> {

}
