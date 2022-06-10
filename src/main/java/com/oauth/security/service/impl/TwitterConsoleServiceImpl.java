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
import com.oauth.security.entity.TwitterConsole;
import com.oauth.security.model.request.TwitterConsoleCreateRequest;
import com.oauth.security.model.request.TwitterConsoleUpdateRequest;
import com.oauth.security.repository.TwitterConsoleRepository;
import com.oauth.security.service.TwitterConsoleService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TwitterConsoleServiceImpl implements TwitterConsoleService {

	private static final String TWITTER_CONSOLE = "twitterConsole";
	private static final String TWITTER_CONSOLE_ALL = "twitterConsoleAll";

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private TwitterConsoleRepository twitterConsoleRepository;

	@Autowired
	private CacheManager cacheManager;

	@SuppressWarnings("unchecked")
	@CachePut(value = "twitterConsole", key = "#twitterConsoleCreateRequest.productId")
	@Override
	public TwitterConsole registerTwitterConsoleUsingPOST(TwitterConsoleCreateRequest twitterConsoleCreateRequest) {
		log.debug("-----TwitterConsoleServiceImpl Class, registerTwitterConsoleUsingPOST method  : {}",
				twitterConsoleCreateRequest);

		Cache cache = cacheManager.getCache(TWITTER_CONSOLE);

		TwitterConsole twitterConsole = searchDuplicateTwitterConsole(twitterConsoleCreateRequest.getProductId());
		if (!ObjectUtils.isEmpty(twitterConsole)) {
			throw new RuntimeException();
		}
		twitterConsole = new TwitterConsole();
		LocalDateTime date = LocalDateTime.now();

		twitterConsole.setAccessToken(twitterConsoleCreateRequest.getAccessToken());
		twitterConsole.setAccessTokenSecret(twitterConsoleCreateRequest.getAccessTokenSecret());
		twitterConsole.setConsumerKey(twitterConsoleCreateRequest.getConsumerKey());
		twitterConsole.setConsumerSecret(twitterConsoleCreateRequest.getConsumerSecret());
		twitterConsole.setProductId(twitterConsoleCreateRequest.getProductId());
		twitterConsole.setUpdatedDate(date);
		twitterConsole.setCreatedDate(date);
		twitterConsole = twitterConsoleRepository.save(twitterConsole);

		if (cache != null) {
			List<TwitterConsole> twitterConsoleList = cache.get(TWITTER_CONSOLE_ALL, List.class);
			if (CollectionUtils.isEmpty(twitterConsoleList)) {
				twitterConsoleList = new ArrayList<>();
			}
			twitterConsoleList.add(twitterConsole);
			cache.put(TWITTER_CONSOLE_ALL, twitterConsoleList);
		}
		return twitterConsole;
	}

	private TwitterConsole searchDuplicateTwitterConsole(String productId) {
		TwitterConsole twitterConsole = null;
		Cache cache = cacheManager.getCache(TWITTER_CONSOLE);
		if (cache != null) {
			twitterConsole = cache.get(productId, TwitterConsole.class);
		}
		if (ObjectUtils.isEmpty(twitterConsole)) {
			log.info("Twitter console not found in the cache.");
			twitterConsole = twitterConsoleRepository.findTwitterConsoleByProductId(productId);
		}
		return twitterConsole;
	}

	@Cacheable(value = "twitterConsole", key = "#productId")
	@Override
	public TwitterConsole retrieveRegisteredTwitterConsoleUsingGET(String productId) {
		log.info("-----TwitterConsoleServiceImpl Class, retrieveRegisteredTwitterConsoleUsingGET method  : {}",
				productId);

		return twitterConsoleRepository.findTwitterConsoleByProductId(productId);
	}

	@SuppressWarnings("unchecked")
	@CacheEvict(value = "twitterConsole", key = "#productId")
	@Override
	public void unregisterTwitterConsoleUingDELETE(String productId) {
		log.info("-----TwitterConsoleServiceImpl Class, unregisterTwitterConsoleUingDELETE method  : {}", productId);
		Cache cache = cacheManager.getCache(TWITTER_CONSOLE);

		TwitterConsole twitterConsole = getTwitterConsoleByProductId(productId);
		log.info("TwitterConole from db : {}", twitterConsole);
		twitterConsoleRepository.delete(twitterConsole);
		Predicate<TwitterConsole> p = twitterCon -> twitterCon.getProductId().equals(twitterConsole.getProductId());

		if (cache != null) {
			List<TwitterConsole> twitterConsoleList = cache.get(TWITTER_CONSOLE_ALL, List.class);
			if (!ObjectUtils.isEmpty(twitterConsoleList)) {
				twitterConsoleList.removeIf(p);
				cache.put(TWITTER_CONSOLE_ALL, twitterConsoleList);
			}
		}

	}

	public TwitterConsole getTwitterConsoleByProductId(String productId) {
		TwitterConsole twitterConsole = null;
		Cache cache = cacheManager.getCache(TWITTER_CONSOLE);
		if (cache != null) {
			twitterConsole = cache.get(productId, TwitterConsole.class);
		}
		if (ObjectUtils.isEmpty(twitterConsole)) {
			twitterConsole = twitterConsoleRepository.findTwitterConsoleByProductId(productId);
		}
		if (ObjectUtils.isEmpty(twitterConsole)) {
			throw new RuntimeException();
		}
		return twitterConsole;
	}

	@SuppressWarnings("unchecked")
	@CachePut(value = "twitterConsole", key = "#productId")
	@Override
	public TwitterConsole updateRegisteredTwitterConsoleUsingPUT(
			TwitterConsoleUpdateRequest twitterConsoleUpdateRequest, String productId)
			throws IOException, ParseException {
		log.debug("-----TwitterConsoleServiceImpl Class, updateRegisteredTwitterConsoleUsingPUT method  : {}----{}",
				twitterConsoleUpdateRequest, productId);
		objectMapper.registerModule(new JavaTimeModule());

		TwitterConsole twitterConsole = getTwitterConsoleByProductId(productId);
		JSONObject twitterConsoleObject = (JSONObject) new JSONParser()
				.parse(objectMapper.writeValueAsString(twitterConsole));

		JSONObject twitterConsoleFromPaylod = (JSONObject) new JSONParser()
				.parse(objectMapper.writeValueAsString(twitterConsoleUpdateRequest));

		for (Object obj : twitterConsoleFromPaylod.keySet()) {
			String param = (String) obj;

			log.info("---Param to be updated : {}", param);
			twitterConsoleObject.put(param, twitterConsoleFromPaylod.get(param));
		}
		twitterConsole = objectMapper.readValue(twitterConsoleObject.toJSONString(), TwitterConsole.class);
		twitterConsole.setUpdatedDate(LocalDateTime.now());
		twitterConsoleRepository.save(twitterConsole);

		String twitterProductId = twitterConsole.getProductId();

		Cache cache = cacheManager.getCache(TWITTER_CONSOLE);

		log.info("TwitterAppId :  {}", twitterProductId);
		Predicate<TwitterConsole> p = twitterCon -> twitterCon.getProductId().equals(twitterProductId);

		if (cache != null) {
			List<TwitterConsole> twitterConsoleList = cache.get(TWITTER_CONSOLE_ALL, List.class);
			if (!ObjectUtils.isEmpty(twitterConsoleList)) {
				twitterConsoleList.removeIf(p);
			}
			if (ObjectUtils.isEmpty(twitterConsoleList)) {
				twitterConsoleList = new ArrayList<>();
			}
			twitterConsoleList.add(twitterConsole);
			cache.put(TWITTER_CONSOLE_ALL, twitterConsoleList);
		}
		return twitterConsole;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TwitterConsole> getAllTwitterConsole() {
		log.debug("-----TwitterConsoleServiceImpl Class, getAllTwitterConsole method ");
		List<TwitterConsole> twitterConsoleList = new ArrayList<>();
		Cache cache = cacheManager.getCache(TWITTER_CONSOLE);
		if (cache != null) {
			twitterConsoleList = cache.get(TWITTER_CONSOLE_ALL, List.class);
		}

		if (CollectionUtils.isEmpty(twitterConsoleList)) {
			twitterConsoleList = twitterConsoleRepository.findAll();
		}
		return twitterConsoleList;

	}
}
