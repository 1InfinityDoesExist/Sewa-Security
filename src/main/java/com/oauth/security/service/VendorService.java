package com.oauth.security.service;

import org.json.simple.parser.ParseException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oauth.security.entity.Vendor;
import com.oauth.security.model.request.VendorUpdateRequest;
import com.oauth.security.model.response.VendorResponse;

@Component
public interface VendorService {

	Vendor retrieveVendorUsingGET(String id);

	Page<Vendor> retrieveAllVendorsUsingGET(Pageable pageable);

	void deleteVendorsUsingDELETE(String id);

	VendorResponse updateVendorsUsingPUT(VendorUpdateRequest vendorUpdateRequest, String id)
			throws JsonProcessingException, ParseException;

}
