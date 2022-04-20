package com.oauth.security.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.oauth.security.controller.OauthClientsController;
import com.oauth.security.model.request.OauthClientsRequest;
import com.oauth.security.model.response.OauthClientsResponse;
import com.oauth.security.service.OauthClientsServcie;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class OauthClientsControllerImpl implements OauthClientsController {

	@Autowired
	private OauthClientsServcie oauthClientsServcie;

	@Override
	public ResponseEntity<OauthClientsResponse> registerClientUsingPOST(OauthClientsRequest clientRequest)
			throws Exception {
		log.info("-----Register Client in the database-----");

		OauthClientsResponse response = oauthClientsServcie.registerClientUsingPOST(clientRequest);

		return ResponseEntity.status(HttpStatus.OK).body(response);

	}

}
