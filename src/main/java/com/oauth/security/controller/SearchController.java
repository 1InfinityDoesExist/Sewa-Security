package com.oauth.security.controller;

import java.io.IOException;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oauth.security.model.request.SearchRequest;
import com.oauth.security.model.response.SearchResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "SearchController", description = "Search API Controller")
public interface SearchController {

	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = SearchResponse.class) })
	@RequestMapping(value = "/search", consumes = { "application/json" }, produces = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<SearchResponse> search(@RequestBody SearchRequest searchRequest,
			@PageableDefault(sort = { "_id" }, direction = Sort.Direction.ASC, size = 10, page = 0) Pageable pageable)
			throws IOException;
}
