package com.oauth.security.repository.mongo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.oauth.security.entity.Vendor;

@Repository
public interface VendorRepository extends MongoRepository<Vendor, String> {

	Vendor findVendorByIdAndIsActive(String id, boolean b);

	Page<Vendor> findVendorByIsActive(boolean b, Pageable pageable);

}
