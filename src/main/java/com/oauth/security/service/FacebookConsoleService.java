package com.oauth.security.service;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.oauth.security.entity.FacebookConsole;
import com.oauth.security.model.request.FacebookConsoleCreateRequest;
import com.oauth.security.model.request.FacebookConsoleUpdateRequest;

@Service
public interface FacebookConsoleService {

	public FacebookConsole registerFacebookConsoleUsingPOST(FacebookConsoleCreateRequest facebookConsoleCreateRequest);

	public FacebookConsole retrieveRegisteredFacebookConsoleUsingGET(String productId);

	public void unregisterFacebookConsoleUingDELETE(String productId);

	public FacebookConsole updateRegisteredFacebookConsoleUsingPUT(
			FacebookConsoleUpdateRequest facebookConsoleUpdateRequest, String productId)
			throws ParseException, IOException;

	public List<FacebookConsole> getAllFacebookConsole();

}
