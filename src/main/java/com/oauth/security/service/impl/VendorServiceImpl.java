package com.oauth.security.service.impl;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oauth.security.entity.Vendor;
import com.oauth.security.model.request.VendorUpdateRequest;
import com.oauth.security.model.response.VendorResponse;
import com.oauth.security.repository.mongo.VendorRepository;
import com.oauth.security.service.VendorService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VendorServiceImpl implements VendorService {

	@Autowired
	private VendorRepository vendorRepostiory;

	private static final ObjectMapper objectMapper;

	static {
		objectMapper = new ObjectMapper();
	}

	@Override
	public Vendor retrieveVendorUsingGET(String id) {
		log.info("-----VendorServiceImpl Class, retrieveVendorUsingGET method.-----");
		Vendor vendor = vendorRepostiory.findVendorByIdAndIsActive(id, true);
		if (ObjectUtils.isEmpty(vendor)) {
			throw new RuntimeException(String.format("Vendor with id %s does not exist.", id));
		}
		return vendor;
	}

	@Override
	public Page<Vendor> retrieveAllVendorsUsingGET(Pageable pageable) {
		log.info("-----VendorServiceImpl Class, retrieveAllVendorsUsingGET method.-----");
		return vendorRepostiory.findVendorByIsActive(true, pageable);
	}

	@Override
	public void deleteVendorsUsingDELETE(String id) {
		log.info("-----VendorServiceImpl Class, deleteVendorsUsingDELETE method.-----");

		Vendor vendor = retrieveVendorUsingGET(id);
		vendor.setActive(false);
		vendorRepostiory.save(vendor);
		return;
	}

	@Override
	public VendorResponse updateVendorsUsingPUT(VendorUpdateRequest vendorUpdateRequest, String id)
			throws JsonProcessingException, ParseException {
		log.info("-----VendorServiceImpl Class, updateVendorsUsingPUT method.-----");
		Vendor vendor = retrieveVendorUsingGET(id);
		JSONObject payloadObject = (JSONObject) new JSONParser()
				.parse(objectMapper.writeValueAsString(vendorUpdateRequest));

		JSONObject dbVendorObject = (JSONObject) new JSONParser().parse(objectMapper.writeValueAsString(vendor));

		for (Object obj : payloadObject.keySet()) {
			String param = (String) obj;
			dbVendorObject.put(param, payloadObject.get(param));
		}

		vendorRepostiory.save(objectMapper.readValue(dbVendorObject.toJSONString(), Vendor.class));

		VendorResponse response = new VendorResponse();
		response.setMsg("Successfully updated.");
		return response;
	}

}
