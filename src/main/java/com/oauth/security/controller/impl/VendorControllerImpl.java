package com.oauth.security.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RestController;

import com.oauth.security.controller.VendorController;
import com.oauth.security.entity.Vendor;
import com.oauth.security.model.request.OTPVerificationRequest;
import com.oauth.security.model.request.RegistrationRequest;
import com.oauth.security.model.request.VendorCreateRequest;
import com.oauth.security.model.request.VendorUpdateRequest;
import com.oauth.security.model.response.OTPVerificationResponse;
import com.oauth.security.model.response.RegistrationResponse;
import com.oauth.security.model.response.VendorResponse;
import com.oauth.security.service.VendorService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class VendorControllerImpl implements VendorController {

	@Autowired
	private VendorService vendorService;

	@Override
	public ResponseEntity<RegistrationResponse> registerEmailUsingPOST(RegistrationRequest registration) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<OTPVerificationResponse> verifyEmailOTPUsingPOST(
			OTPVerificationRequest oTPVerificationRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<RegistrationResponse> registerMobileUsingPOST(RegistrationRequest registration) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<OTPVerificationResponse> verifyMobileOTPUsingPOST(
			OTPVerificationRequest oTPVerificationRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<VendorResponse> createVendorsUsingPOST(VendorCreateRequest vendorCreateRequest)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Vendor> retrieveVendorUsingGET(String id) throws Exception {
		log.info("-----VendorControllerImpl Class, retrieveVendorUsingGET method----");

		return ResponseEntity.status(HttpStatus.OK).body(vendorService.retrieveVendorUsingGET(id));
	}

	@Override
	public ResponseEntity<ModelMap> deleteVendorsUsingDELETE(String id) throws Exception {
		log.info("-----VendorControllerImpl Class, deleteVendorsUsingDELETE method----");
		vendorService.deleteVendorsUsingDELETE(id);
		return ResponseEntity.status(HttpStatus.OK).body(new ModelMap().addAttribute("msg", "success"));
	}

	@Override
	public ResponseEntity<Page<Vendor>> retrieveAllVendorsUsingGET(Pageable pageable) throws Exception {
		log.info("-----VendorControllerImpl Class, retrieveAllVendorsUsingGET method----");
		return ResponseEntity.status(HttpStatus.OK).body(vendorService.retrieveAllVendorsUsingGET(pageable));
	}

	@Override
	public ResponseEntity<VendorResponse> updateVendorsUsingPUT(VendorUpdateRequest vendorUpdateRequest, String id)
			throws Exception {
		log.info("-----VendorControllerImpl Class, updateVendorsUsingPUT method----");
		return ResponseEntity.status(HttpStatus.OK).body(vendorService.updateVendorsUsingPUT(vendorUpdateRequest, id));
	}

}
