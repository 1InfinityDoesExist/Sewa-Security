package com.oauth.security.controller.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.oauth.security.controller.VendorController;
import com.oauth.security.model.request.OTPVerificationRequest;
import com.oauth.security.model.request.RegistrationRequest;
import com.oauth.security.model.request.VendorRequest;
import com.oauth.security.model.response.OTPVerificationResponse;
import com.oauth.security.model.response.RegistrationResponse;
import com.oauth.security.model.response.VendorResponse;

@RestController
public class VendorControllerImpl implements VendorController {

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
	public ResponseEntity<VendorResponse> registerVendorsUsingPOST(VendorRequest vendorRequest) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
