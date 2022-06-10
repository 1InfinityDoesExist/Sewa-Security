package com.oauth.security.controller.socialLogin;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.oauth.security.entity.TwitterConsole;
import com.oauth.security.service.TwitterConsoleService;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;

@Slf4j
@RestController
public class TwitterLogin {

	@Autowired
	private TwitterConsoleService twitterConsoleService;

	/**
	 * 
	 * @param productId
	 * @param request
	 * @return
	 * @throws UnirestException
	 * @throws UnsupportedEncodingException
	 * @throws GeneralSecurityException
	 * @throws TwitterException
	 */
	@GetMapping(path = "/{productId}/gaian/twitterOauth")
	public RedirectView twitterLogin(@PathVariable(value = "productId", required = true) String productId,
			HttpServletRequest request)
			throws UnirestException, UnsupportedEncodingException, GeneralSecurityException, TwitterException {
		log.info("SocialLoginHelperContorller Class, twitterLogin method.");
		RedirectView redirectView = new RedirectView();
		String url = twitterLoginURL(productId, request);
		redirectView.setUrl(url);
		return redirectView;
	}

	/**
	 * 
	 * @param productId
	 * @param request
	 * @return
	 * @throws TwitterException
	 */
	private String twitterLoginURL(String productId, HttpServletRequest request) throws TwitterException {

		TwitterConsole twitterConsole = twitterConsoleService.retrieveRegisteredTwitterConsoleUsingGET(productId);
		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(twitterConsole.getConsumerKey(), twitterConsole.getConsumerSecret());

		String baseURL = request.getRequestURL().toString();
		baseURL = baseURL.replace(request.getScheme() + "://", "");

		log.info("----Callback : {}",
				request.getScheme() + "://" + baseURL.substring(0, baseURL.indexOf("/")) + "/twitter-callback");

		RequestToken requestToken = twitter.getOAuthRequestToken(
				request.getScheme() + "://" + baseURL.substring(0, baseURL.indexOf("/")) + "/twitter-callback");

		String token = requestToken.getToken();

		return "https://api.twitter.com/oauth/authenticate?oauth_token=" + token;
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
	@ApiIgnore
	@GetMapping(path = "/twitter-callback")
	@ResponseBody
	public ResponseEntity<?> getTwitterAuthCodeAndToken(@RequestParam(value = "oauth_token") String oauth_token,
			@RequestParam(value = "oauth_verifier") String oauth_verifier) throws ServletException, IOException {

		return ResponseEntity.status(HttpStatus.OK).body(
				new ModelMap().addAttribute("oauth_token", oauth_token).addAttribute("oauth_verifier", oauth_verifier));
	}

}
