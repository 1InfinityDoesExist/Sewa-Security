package com.oauth.security.service;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.oauth.security.entity.TwitterConsole;
import com.oauth.security.model.request.TwitterConsoleCreateRequest;
import com.oauth.security.model.request.TwitterConsoleUpdateRequest;

@Service
public interface TwitterConsoleService {

	public TwitterConsole registerTwitterConsoleUsingPOST(TwitterConsoleCreateRequest twitterConsoleCreateRequest);

	public TwitterConsole retrieveRegisteredTwitterConsoleUsingGET(String productId);

	public void unregisterTwitterConsoleUingDELETE(String productId);

	public TwitterConsole updateRegisteredTwitterConsoleUsingPUT(
			TwitterConsoleUpdateRequest twitterConsoleUpdateRequest, String productId)
			throws IOException, ParseException;

	public List<TwitterConsole> getAllTwitterConsole();

}
