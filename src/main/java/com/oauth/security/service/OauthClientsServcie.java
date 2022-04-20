package com.oauth.security.service;

import org.springframework.stereotype.Component;

import com.oauth.security.model.request.OauthClientsRequest;
import com.oauth.security.model.response.OauthClientsResponse;

@Component
public interface OauthClientsServcie {

	public OauthClientsResponse registerClientUsingPOST(OauthClientsRequest clientRequest);

}
