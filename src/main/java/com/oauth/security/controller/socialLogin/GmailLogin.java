package com.oauth.security.controller.socialLogin;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfo;
import com.oauth.security.entity.GoogleConsole;
import com.oauth.security.repository.mongo.GoogleConsoleRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class GmailLogin {

	@Autowired
	private GoogleConsoleRepository googleConsoleRepository;

	/**
	 * 
	 * @param productId
	 * @return
	 */
	@GetMapping(path = "/{productId}/gaian/googleOauth")
	public RedirectView googleLogin(@PathVariable(value = "productId", required = true) String productId) {
		log.info("SocialLoginHelperContorller Class, googleLogin method.");
		RedirectView redirectView = new RedirectView();
		String url = googleLoginURL(productId);
		redirectView.setUrl(url);
		return redirectView;
	}

	public GoogleConsole getGoogleConsole(String productId) {
		GoogleConsole googleConsole = googleConsoleRepository.findGoogleConsoleByProductId(productId);
		log.info("FacebookConsole : {}", googleConsole);
		return googleConsole;
	}

	public GoogleAuthorizationCodeFlow createGoogleConnection(String productId) {
		log.info("GoogleConsoleServiceImp Class, createGoogleConnection method");

		GoogleConsole googleConsole = getGoogleConsole(productId);

		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(new NetHttpTransport(),
				new JacksonFactory(), googleConsole.getGoogleAppId(), googleConsole.getGoogleAppSecret(),
				Arrays.asList("email", "profile")).build();
		log.info("-----Returning Google Connection factory-----");
		return flow;
	}

	public String googleLoginURL(String productId) {
		log.info("GoogleConsoleServiceImp Class, googleLogin method");

		GoogleConsole googleConsole = getGoogleConsole(productId);

		return createGoogleConnection(productId).newAuthorizationUrl()
				.setRedirectUri(googleConsole.getGoogleAppRedirectUrl()).build();
	}

	/**
	 * 
	 * @param productId
	 * @param code
	 * @param model
	 * @param request
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@GetMapping(path = "/{productId}/login/oauth2/code/google")
	public ResponseEntity<?> getGoolgeAuthCodeAndToken(@PathVariable(value = "productId") String productId,
			@RequestParam("code") String code, ModelMap model, HttpServletRequest request)
			throws ServletException, IOException {
		log.info("SocialLoginHelperContorller Class, getGoolgeAuthCodeAndToken method.");
		String accessToken = getGoogleAccessToken(code, productId);

		return ResponseEntity.status(HttpStatus.OK).body(new ModelMap().addAttribute("google_token", accessToken));

	}

	/**
	 * 
	 * @param code
	 * @param productId
	 * @return
	 * @throws IOException
	 */
	public String getGoogleAccessToken(String code, String productId) throws IOException {
		log.info("GoogleConsoleServiceImp Class, getGoogleAccessToken method");
		GoogleConsole googleConsole = getGoogleConsole(productId);

		GoogleTokenResponse authorizationCode = createGoogleConnection(productId).newTokenRequest(code)
				.setGrantType("authorization_code").setRedirectUri(googleConsole.getGoogleAppRedirectUrl()).execute();
		String token = authorizationCode.getIdToken();
		getGoogleUserProfile(authorizationCode, productId);
		return token;
	}

	/**
	 * 
	 * @param authorizationCode
	 * @param productId
	 * @throws IOException
	 */
	public void getGoogleUserProfile(GoogleTokenResponse authorizationCode, String productId) throws IOException {
		log.info("GoogleConsoleServiceImp Class, getGoogleUserProfile method");
		final Credential credential = new GoogleCredential().setFromTokenResponse(authorizationCode);

		Oauth2 userInfoService = new Oauth2.Builder(new NetHttpTransport(), new JacksonFactory(), credential).build();
		Userinfo userInfo = null;
		try {
			userInfoService.userinfo().get().execute();
		} catch (Exception e) {
			log.debug("UserInfo : {}", userInfo);
		}
	}

}
