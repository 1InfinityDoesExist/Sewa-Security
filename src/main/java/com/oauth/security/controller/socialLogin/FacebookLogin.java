package com.oauth.security.controller.socialLogin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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

import com.oauth.security.entity.FacebookConsole;
import com.oauth.security.repository.FacebookConsolerRepository;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

@Slf4j
@RestController
public class FacebookLogin {

	@Autowired
	private FacebookConsolerRepository facebookConsoleRepository;

	/**
	 * 
	 * @param productId
	 * @return
	 */
	@GetMapping(path = "/{productId}/gaian/facebookOauth")
	public RedirectView loginViaFacebook(@PathVariable(value = "productId", required = true) String productId) {
		log.info("FacebookConsoleControllerImpl Class, loginViaFacebook method.");
		String fbLoginUrl = getFbLoginUrl(productId);
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(fbLoginUrl);
		log.info("-----fbLoginUrl {}", fbLoginUrl);
		return redirectView;
	}

	/**
	 * 
	 * 
	 * Login / Sign Up via Facebook AP 2020/Sep/07
	 */
	public String getFbLoginUrl(String productId) {
		log.info("FacebookConsoleServiceImpl Class, getFbLoginUrl method");
		String fbLoginUrl = "";

		FacebookConsole fbConsole = facebookConsoleRepository.findFacebookConsoleByProductId(productId);
		try {
			fbLoginUrl = "http://www.facebook.com/dialog/oauth?" + "client_id=" + fbConsole.getFacebookAppId()
					+ "&redirect_uri=" + URLEncoder.encode(fbConsole.getFacebookAppRedirectUrl(), "UTF-8")
					+ "&scope=email";
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return fbLoginUrl;
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
	@GetMapping(path = "/{productId}/login/oauth2/code/facebook")
	@ResponseBody
	public ResponseEntity<?> getFacebookAuthCodeAndToken(@PathVariable(value = "productId") String productId,
			@RequestParam("code") String code, ModelMap model, HttpServletRequest request)
			throws ServletException, IOException {
		log.info("SocialLoginHelperContorller Class, getGoolgeAuthCodeAndToken method.");
		String fbGraphUrl = getFBGraphUrl(code, productId);
		log.info("-----TenantFacebookAuthController -> fbGraphUrl {}", fbGraphUrl);

		String fbAccessToken = getAccessToken(code, productId);

		return ResponseEntity.status(HttpStatus.OK).body(new ModelMap().addAttribute("facebook_token", fbAccessToken));
	}

	/**
	 * Creates the authorization_code URL
	 * 
	 * 
	 * @param code
	 * @param productId
	 * @return
	 */
	public String getFBGraphUrl(String code, String productId) {
		log.info("FacebookConsoleServiceImpl Class, getFBGraphUrl method");
		log.info(" Code {}", code);
		String fbGraphUrl = "";

		FacebookConsole fbConsole = facebookConsoleRepository.findFacebookConsoleByProductId(productId);

		try {
			log.info("---Generating fbGraphURL -----");
			fbGraphUrl = "https://graph.facebook.com/oauth/access_token?" + "client_id=" + fbConsole.getFacebookAppId()
					+ "&redirect_uri=" + URLEncoder.encode(fbConsole.getFacebookAppRedirectUrl(), "UTF-8")
					+ "&client_secret=" + fbConsole.getFacebookAppSecret() + "&code=" + code;
			log.info("-----Successfully generated fbGraphURL-----");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		log.info("-----SuccessFully Generated GraphUrl {}", fbGraphUrl);
		return fbGraphUrl;
	}

	/**
	 * Calls the oauth_token api where grant_type is authorization_code.
	 * 
	 * 
	 * @param code
	 * @param productId
	 * @return
	 */
	public String getAccessToken(String code, String productId) {
		log.info("FacebookConsoleServiceImpl Class, getAccessToken method");
		String accessToken = "";
		if ("".equals(accessToken)) {
			URL fbGraphURL;
			try {
				fbGraphURL = new URL(getFBGraphUrl(code, productId));
			} catch (MalformedURLException e) {
				throw new RuntimeException();
			}
			URLConnection fbConnection;
			StringBuilder b = null;
			try {
				fbConnection = fbGraphURL.openConnection();
				log.info("-----FbGraphURL.openConnection() ");
				BufferedReader in;
				in = new BufferedReader(new InputStreamReader(fbConnection.getInputStream()));

				String inputLine;
				b = new StringBuilder();
				while ((inputLine = in.readLine()) != null) {
					b.append(inputLine + "\n");
				}
				log.info(b.toString());
				in.close();

			} catch (IOException e) {
				throw new RuntimeException();
			}
			accessToken = b.toString();
			try {
				JSONObject accessTokenObject = (JSONObject) new JSONParser().parse(accessToken);
				for (Object object : accessTokenObject.keySet()) {
					String param = (String) object;
					if (param.equalsIgnoreCase("access_token")) {
						accessToken = (String) accessTokenObject.get(param);
						break;
					}
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return accessToken;
	}
}
