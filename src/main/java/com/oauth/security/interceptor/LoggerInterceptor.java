package com.oauth.security.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoggerInterceptor implements HandlerInterceptor {

	@Autowired
	CacheManager cacheManager;

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		String token = request.getHeader("token");
		log.info("token : {}", token);
		if (ObjectUtils.isEmpty(token)) {
			if (!ObjectUtils.isEmpty(request.getHeader("authorization"))) {
				token = request.getHeader("authorization").split(" ")[1];
			} else {
				throw new RuntimeException("Authorization Bearer token must not be null or empty.");
			}
		}
		JSONObject jsonObject = parseToken(token);
		String key = (String) jsonObject.get("jti");
		System.out.println(key);

		Cache cache = cacheManager.getCache("blacklist");
		Boolean isBlackListed = cache.get(key, Boolean.class);
		if (isBlackListed) {
			throw new RuntimeException("------Session Expired-----------");
		}

		log.info("-----After Completion" + request.getRequestURL());
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

		log.info("-----Post Handler" + request.getRequestURL());
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub

		log.info("-----Pre Handler" + request.getRequestURL());

		return HandlerInterceptor.super.preHandle(request, response, handler);

	}

	public JSONObject parseToken(String jwtToken) throws JsonParseException, JsonMappingException, IOException {
		log.info("-----ValidateUserWithToken Class, parseToken method-----");
		JSONObject tokenBody;
		log.info("Parsing token : {}", jwtToken);
		String[] splitString = jwtToken.split("\\.");
		if (splitString.length < 3) {
			log.debug("Not valid token");
			throw new RuntimeException("Token is either expired or is invalid. Please insert a new token.");
		}
		Base64 base64Url = new Base64(true);
		String body = new String(base64Url.decode(splitString[1]));
		tokenBody = new ObjectMapper().readValue(body, JSONObject.class);

		log.info("Token body {}", tokenBody);
		return tokenBody;
	}
}
