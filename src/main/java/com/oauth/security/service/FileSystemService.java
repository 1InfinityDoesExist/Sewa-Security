package com.oauth.security.service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.oauth.security.model.request.ContentRequest;
import com.oauth.security.model.response.ContentResponse;

@Component
public interface FileSystemService {

	public ContentResponse uploadFile(ContentRequest request) throws IOException;

	public String getContentUri(UUID id);
}
