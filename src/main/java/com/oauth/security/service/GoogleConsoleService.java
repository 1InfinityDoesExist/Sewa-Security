package com.oauth.security.service;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.oauth.security.entity.GoogleConsole;
import com.oauth.security.model.request.GoogleConsoleCreateRequest;
import com.oauth.security.model.request.GoogleConsoleUpdateRequest;

@Service
public interface GoogleConsoleService {

	public GoogleConsole registerGoogleConsoleUsingPOST(GoogleConsoleCreateRequest googleConsoleCreateRequest);

	public GoogleConsole retrieveRegisteredGoogleConsoleUsingGET(String productId);

	public void unregisterGoogleConsoleUingDELETE(String productId);

	public GoogleConsole updateRegisteredGoogleConsoleUsingPUT(GoogleConsoleUpdateRequest googleConsoleUpdateRequest,
			String productId) throws ParseException, IOException;

	public List<GoogleConsole> getAllGoogleConsole();
}
