package com.oauth.security.service.impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.oauth.security.entity.FacebookConsole;
import com.oauth.security.model.request.FacebookConsoleCreateRequest;
import com.oauth.security.model.request.FacebookConsoleUpdateRequest;
import com.oauth.security.repository.mongo.FacebookConsolerRepository;
import com.oauth.security.service.FacebookConsoleService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FacebookConsoleServiceImpl implements FacebookConsoleService {

	private static final String FACEBOOK_CONSOLE = "facebookConsole";
	private static final String FACEBOOK_CONSOLE_ALL = "facebookConsoleAll";

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private FacebookConsolerRepository facebookConsoleRepository;

	@Autowired
	private CacheManager cacheManager;

	@SuppressWarnings("unchecked")
	@CachePut(value = "facebookConsole", key = "#facebookConsoleCreateRequest.productId")
	@Override
	public FacebookConsole registerFacebookConsoleUsingPOST(FacebookConsoleCreateRequest facebookConsoleCreateRequest) {
		log.debug("-----FacebookConsoleServiceImpl Class, registerFacebookConsoleUsingPOST method  : {}",
				facebookConsoleCreateRequest);

		Cache cache = cacheManager.getCache(FACEBOOK_CONSOLE);

		FacebookConsole facebookConsole = searchDuplicateFacebookConsoleByProductId(
				facebookConsoleCreateRequest.getProductId());

		if (!ObjectUtils.isEmpty(facebookConsole)) {
			throw new RuntimeException();
		}
		facebookConsole = new FacebookConsole();
		facebookConsole.setFacebookAppId(facebookConsoleCreateRequest.getFacebookAppId());
		facebookConsole.setFacebookAppRedirectUrl(facebookConsoleCreateRequest.getFacebookAppRedirectUrl());
		facebookConsole.setFacebookAppSecret(facebookConsoleCreateRequest.getFacebookAppSecret());
		facebookConsole.setProductId(facebookConsoleCreateRequest.getProductId());

		LocalDateTime date = LocalDateTime.now();
		facebookConsole.setUpdatedDate(date);
		facebookConsole.setCreatedDate(date);
		FacebookConsole fbConsole = facebookConsoleRepository.save(facebookConsole);
		log.info("Facebook Console after persisting in db : {}", fbConsole);
		if (cache != null) {
			List<FacebookConsole> facebookConsoleList = cache.get(FACEBOOK_CONSOLE_ALL, List.class);
			if (CollectionUtils.isEmpty(facebookConsoleList)) {
				facebookConsoleList = new ArrayList<>();
			}
			facebookConsoleList.add(fbConsole);
			cache.put(FACEBOOK_CONSOLE_ALL, facebookConsoleList);
		}
		return fbConsole;

	}

	public FacebookConsole searchDuplicateFacebookConsoleByProductId(String productId) {
		FacebookConsole fbConsole = null;
		Cache cache = cacheManager.getCache(FACEBOOK_CONSOLE);
		if (cache != null) {
			fbConsole = cache.get(productId, FacebookConsole.class);
		}
		if (ObjectUtils.isEmpty(fbConsole)) {
			log.info("-----Facebook Console not found in cache.");
			fbConsole = facebookConsoleRepository.findFacebookConsoleByProductId(productId);
		}

		return fbConsole;
	}

	@Cacheable(value = "facebookConsole", key = "#productId")
	@Override
	public FacebookConsole retrieveRegisteredFacebookConsoleUsingGET(String productId) {
		log.info("-----FacebookConsoleServiceImpl Class, retrieveRegisteredFacebookConsoleUsingGET method  : {}",
				productId);
		return facebookConsoleRepository.findFacebookConsoleByProductId(productId);
	}

	@SuppressWarnings("unchecked")
	@CacheEvict(value = "facebookConsole", key = "#productId")
	@Override
	public void unregisterFacebookConsoleUingDELETE(String productId) {
		log.debug("-----FacebookConsoleServiceImpl Class, unregisterFacebookConsoleUingDELETE method  : {}", productId);
		Cache cache = cacheManager.getCache(FACEBOOK_CONSOLE);

		FacebookConsole fbConsole = getFacebookConsoleByProductId(productId);
		log.info("FacebookConole from db : {}", fbConsole);

		Predicate<FacebookConsole> p = fbCon -> fbCon.getProductId().equals(fbConsole.getProductId());

		if (cache != null) {
			List<FacebookConsole> fbConsoleList = cache.get(FACEBOOK_CONSOLE_ALL, List.class);
			if (!ObjectUtils.isEmpty(fbConsoleList)) {
				fbConsoleList.removeIf(p);
				cache.put(FACEBOOK_CONSOLE_ALL, fbConsoleList);
			}
		}

		facebookConsoleRepository.delete(fbConsole);

	}

	public FacebookConsole getFacebookConsoleByProductId(String productId) {
		FacebookConsole fbConsole = null;
		Cache cache = cacheManager.getCache(FACEBOOK_CONSOLE);
		if (cache != null) {
			fbConsole = cache.get(productId, FacebookConsole.class);
		}
		if (ObjectUtils.isEmpty(fbConsole)) {
			log.info("-----Facebook Console not found in cache.");
			fbConsole = facebookConsoleRepository.findFacebookConsoleByProductId(productId);
		}
		if (ObjectUtils.isEmpty(fbConsole)) {
			throw new RuntimeException();
		}
		return fbConsole;
	}

	@SuppressWarnings("unchecked")
	@CachePut(value = "facebookConsole", key = "#productId")
	@Override
	public FacebookConsole updateRegisteredFacebookConsoleUsingPUT(
			FacebookConsoleUpdateRequest facebookConsoleUpdateRequest, String productId)
			throws ParseException, IOException {
		log.debug("-----FacebookConsoleServiceImpl Class, updateRegisteredFacebookConsoleUsingPUT method  : {}----{}",
				facebookConsoleUpdateRequest, productId);

		objectMapper.registerModule(new JavaTimeModule());

		FacebookConsole fbConsole = getFacebookConsoleByProductId(productId);

		log.debug("Facebook console : {}", fbConsole);
		JSONObject fbConsoleObject = (JSONObject) new JSONParser().parse(objectMapper.writeValueAsString(fbConsole));

		JSONObject fbConsoleFromPaylod = (JSONObject) new JSONParser()
				.parse(objectMapper.writeValueAsString(facebookConsoleUpdateRequest));

		for (Object obj : fbConsoleFromPaylod.keySet()) {
			String param = (String) obj;

			log.info("---Param to be updated : {}", param);
			fbConsoleObject.put(param, fbConsoleFromPaylod.get(param));
		}
		fbConsole = objectMapper.readValue(fbConsoleObject.toJSONString(), FacebookConsole.class);
		fbConsole.setCreatedDate(fbConsole.getCreatedDate());
		fbConsole.setUpdatedDate(LocalDateTime.now());
		facebookConsoleRepository.save(fbConsole);

		String fbProductId = fbConsole.getProductId();

		Cache cache = cacheManager.getCache(FACEBOOK_CONSOLE);

		log.info("fbAppId :  {}", fbProductId);
		Predicate<FacebookConsole> p = fbCon -> fbCon.getProductId().equals(fbProductId);

		if (cache != null) {
			List<FacebookConsole> fbConsoleList = cache.get(FACEBOOK_CONSOLE_ALL, List.class);
			if (!ObjectUtils.isEmpty(fbConsoleList)) {
				fbConsoleList.removeIf(p);
			}
			if (ObjectUtils.isEmpty(fbConsoleList)) {
				fbConsoleList = new ArrayList<>();
			}
			fbConsoleList.add(fbConsole);
			cache.put(FACEBOOK_CONSOLE_ALL, fbConsoleList);
		}
		return fbConsole;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FacebookConsole> getAllFacebookConsole() {
		log.debug("-----FacebookConsoleServiceImpl Class, getAllFacebookConsole method ");
		List<FacebookConsole> fbConsoleList = new ArrayList<>();
		Cache cache = cacheManager.getCache(FACEBOOK_CONSOLE);
		if (cache != null) {
			fbConsoleList = cache.get(FACEBOOK_CONSOLE_ALL, List.class);
		}

		if (CollectionUtils.isEmpty(fbConsoleList)) {
			fbConsoleList = facebookConsoleRepository.findAll();
		}
		return fbConsoleList;
	}
}
