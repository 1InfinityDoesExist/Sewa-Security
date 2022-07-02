package com.oauth.security.controller.impl;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.oauth.security.controller.FileSystemController;
import com.oauth.security.model.request.ContentRequest;
import com.oauth.security.model.response.ContentResponse;
import com.oauth.security.service.FileSystemService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class FileSystemControllerImpl implements FileSystemController {

	@Autowired
	private FileSystemService fileSystemService;

	@Override
	public ResponseEntity<ContentResponse> uploadContentUsingPOST(MultipartFile file, String filePath,
			List<String> tags, String fileName, String description, Boolean overrideFile,
			HttpServletRequest httpServletRequest) throws IOException {
		log.info("----Method to upload content to the given filePath.----");

		if (file.isEmpty()) {
			throw new RuntimeException("File must not be null or empty.");
		}

		return ResponseEntity.status(HttpStatus.OK)
				.body(fileSystemService.uploadFile(ContentRequest.builder().description(description).file(file)
						.tags(tags).fileName(fileName).filePath(filePath).httpServletRequest(httpServletRequest)
						.overrideFile(overrideFile).build()));
	}

	@Override
	public ResponseEntity<byte[]> getContentUsingGET(UUID id, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void redirectURLUsingGET(UUID id, HttpServletResponse response) throws Exception {
		response.sendRedirect(fileSystemService.getContentUri(id));

	}
}
