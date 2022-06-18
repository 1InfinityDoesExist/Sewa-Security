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
import com.oauth.security.entity.GoogleConsole;
import com.oauth.security.model.request.GoogleConsoleCreateRequest;
import com.oauth.security.model.request.GoogleConsoleUpdateRequest;
import com.oauth.security.repository.mongo.GoogleConsoleRepository;
import com.oauth.security.service.GoogleConsoleService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class GoogleConsoleServiceImp implements GoogleConsoleService {

	private static final String GOOGLE_CONSOLE = "googleConsole";
	private static final String GOOGLE_CONSOLE_ALL = "googleConsoleAll";

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private GoogleConsoleRepository googleConsoleRepository;

	@Autowired
	private CacheManager cacheManager;

	@SuppressWarnings("unchecked")
	@CachePut(value = "googleConsole", key = "#googleConsoleCreateRequest.productId")
	@Override
	public GoogleConsole registerGoogleConsoleUsingPOST(GoogleConsoleCreateRequest googleConsoleCreateRequest) {
		log.debug("-----GoogleConsoleServiceImpl Class, registerGoogleConsoleUsingPOST method  : {}",
				googleConsoleCreateRequest);

		Cache cache = cacheManager.getCache(GOOGLE_CONSOLE);

		GoogleConsole googleConsole = searchDuplicateGoogleConsole(googleConsoleCreateRequest.getProductId());
		if (!ObjectUtils.isEmpty(googleConsole)) {
			throw new RuntimeException();
		}
		googleConsole = new GoogleConsole();
		googleConsole.setGoogleAppId(googleConsoleCreateRequest.getGoogleAppId());
		googleConsole.setGoogleAppRedirectUrl(googleConsoleCreateRequest.getGoogleAppRedirectUrl());
		googleConsole.setGoogleAppSecret(googleConsoleCreateRequest.getGoogleAppSecret());
		googleConsole.setProductId(googleConsoleCreateRequest.getProductId());

		LocalDateTime date = LocalDateTime.now();
		googleConsole.setUpdatedDate(date);
		googleConsole.setCreatedDate(date);
		googleConsole = googleConsoleRepository.save(googleConsole);

		if (cache != null) {
			List<GoogleConsole> googleConsoleList = cache.get(GOOGLE_CONSOLE_ALL, List.class);
			if (CollectionUtils.isEmpty(googleConsoleList)) {
				googleConsoleList = new ArrayList<>();
			}
			googleConsoleList.add(googleConsole);
			cache.put(GOOGLE_CONSOLE_ALL, googleConsoleList);
		}
		return googleConsole;

	}

	public GoogleConsole searchDuplicateGoogleConsole(String productId) {
		GoogleConsole googleConsole = null;
		Cache cache = cacheManager.getCache(GOOGLE_CONSOLE);
		if (cache != null) {
			googleConsole = cache.get(productId, GoogleConsole.class);
		}
		if (ObjectUtils.isEmpty(googleConsole)) {
			log.info("Google console not found in the cache.");
			googleConsole = googleConsoleRepository.findGoogleConsoleByProductId(productId);
		}
		return googleConsole;
	}

	@Cacheable(value = "googleConsole", key = "#productId")
	@Override
	public GoogleConsole retrieveRegisteredGoogleConsoleUsingGET(String productId) {
		log.info("-----GoogleConsoleServiceImpl Class, retrieveRegisteredGoogleConsoleUsingGET method  : {}",
				productId);
		return googleConsoleRepository.findGoogleConsoleByProductId(productId);
	}

	@SuppressWarnings("unchecked")
	@CacheEvict(value = "googleConsole", key = "#productId")
	@Override
	public void unregisterGoogleConsoleUingDELETE(String productId) {
		log.info("-----GoogleConsoleServiceImpl Class, unregisterGoogleConsoleUingDELETE method  : {}", productId);
		Cache cache = cacheManager.getCache(GOOGLE_CONSOLE);

		GoogleConsole googleConsole = getGoogleConsoleByProductId(productId);
		log.info("GoogleConole from db : {}", googleConsole);
		Predicate<GoogleConsole> p = googleCon -> googleCon.getProductId().equals(googleConsole.getProductId());

		if (cache != null) {
			List<GoogleConsole> googleConsoleList = cache.get(GOOGLE_CONSOLE_ALL, List.class);
			if (!ObjectUtils.isEmpty(googleConsoleList)) {
				googleConsoleList.removeIf(p);
				cache.put(GOOGLE_CONSOLE_ALL, googleConsoleList);
			}
		}
		googleConsoleRepository.delete(googleConsole);

	}

	public GoogleConsole getGoogleConsoleByProductId(String productId) {
		GoogleConsole googleConsole = null;
		Cache cache = cacheManager.getCache(GOOGLE_CONSOLE);
		if (cache != null) {
			googleConsole = cache.get(productId, GoogleConsole.class);
		}
		if (ObjectUtils.isEmpty(googleConsole)) {
			googleConsole = googleConsoleRepository.findGoogleConsoleByProductId(productId);
		}
		if (ObjectUtils.isEmpty(googleConsole)) {
			throw new RuntimeException();
		}
		return googleConsole;
	}

	@SuppressWarnings("unchecked")
	@CachePut(value = "googleConsole", key = "#productId")
	@Override
	public GoogleConsole updateRegisteredGoogleConsoleUsingPUT(GoogleConsoleUpdateRequest googleConsoleUpdateRequest,
			String productId) throws ParseException, IOException {
		log.debug("-----GoogleConsoleServiceImpl Class, updateRegisteredGoogleConsoleUsingPUT method  : {}----{}",
				googleConsoleUpdateRequest, productId);
		objectMapper.registerModule(new JavaTimeModule());

		GoogleConsole googleConsole = getGoogleConsoleByProductId(productId);
		JSONObject googleConsoleObject = (JSONObject) new JSONParser()
				.parse(objectMapper.writeValueAsString(googleConsole));

		JSONObject googleConsoleFromPaylod = (JSONObject) new JSONParser()
				.parse(objectMapper.writeValueAsString(googleConsoleUpdateRequest));

		for (Object obj : googleConsoleFromPaylod.keySet()) {
			String param = (String) obj;

			log.info("---Param to be updated : {}", param);
			googleConsoleObject.put(param, googleConsoleFromPaylod.get(param));
		}
		googleConsole = objectMapper.readValue(googleConsoleObject.toJSONString(), GoogleConsole.class);
		googleConsole.setUpdatedDate(LocalDateTime.now());
		googleConsoleRepository.save(googleConsole);

		String googleProductId = googleConsole.getProductId();

		Cache cache = cacheManager.getCache(GOOGLE_CONSOLE);

		log.info("googleAppId :  {}", googleProductId);
		Predicate<GoogleConsole> p = googleCon -> googleCon.getProductId().equals(googleProductId);

		if (cache != null) {
			List<GoogleConsole> googleConsoleList = cache.get(GOOGLE_CONSOLE_ALL, List.class);
			if (!ObjectUtils.isEmpty(googleConsoleList)) {
				googleConsoleList.removeIf(p);
			}
			if (ObjectUtils.isEmpty(googleConsoleList)) {
				googleConsoleList = new ArrayList<>();
			}
			googleConsoleList.add(googleConsole);
			cache.put(GOOGLE_CONSOLE_ALL, googleConsoleList);
		}
		return googleConsole;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GoogleConsole> getAllGoogleConsole() {
		log.debug("-----GoogleConsoleServiceImpl Class, getAllGoogleConsole method ");
		List<GoogleConsole> googleConsoleList = new ArrayList<>();
		Cache cache = cacheManager.getCache(GOOGLE_CONSOLE);
		if (cache != null) {
			googleConsoleList = cache.get(GOOGLE_CONSOLE_ALL, List.class);
		}

		if (CollectionUtils.isEmpty(googleConsoleList)) {
			googleConsoleList = googleConsoleRepository.findAll();
		}
		return googleConsoleList;

	}
}
