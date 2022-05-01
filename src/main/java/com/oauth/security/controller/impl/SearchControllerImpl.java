package com.oauth.security.controller.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.oauth.security.controller.SearchController;
import com.oauth.security.model.request.SearchRequest;
import com.oauth.security.model.response.SearchResponse;
import com.oauth.security.service.SearchService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SearchControllerImpl implements SearchController {

	@Autowired
	private SearchService searchService;

	@Override
	public ResponseEntity<SearchResponse> search(SearchRequest searchRequest, Pageable pageable) throws IOException {
		log.info("----SearchControllerImpl Class, search method.----");
		return ResponseEntity.status(HttpStatus.OK).body(searchService.searchUsingPOST(searchRequest, pageable));
	}

}
