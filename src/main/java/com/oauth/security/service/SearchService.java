package com.oauth.security.service;

import java.io.IOException;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.oauth.security.model.request.SearchRequest;
import com.oauth.security.model.response.SearchResponse;

@Service
public interface SearchService {

	public SearchResponse searchUsingPOST(SearchRequest searchRequest, Pageable pageable) throws IOException;
}
