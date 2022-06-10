package com.oauth.security.service;

import org.springframework.stereotype.Component;

import com.oauth.security.model.request.ContentRequest;
import com.oauth.security.model.response.ContentResponse;

@Component
public interface FileSystemService {
	
	public ContentResponse uploadFile(ContentRequest request);
}
