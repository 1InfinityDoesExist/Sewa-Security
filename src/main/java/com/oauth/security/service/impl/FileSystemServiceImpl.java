package com.oauth.security.service.impl;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.oauth.security.model.request.ContentRequest;
import com.oauth.security.model.response.ContentResponse;
import com.oauth.security.service.FileSystemService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileSystemServiceImpl implements FileSystemService {

	@Override
	public ContentResponse uploadFile(ContentRequest request) {
		log.info("-----FileSystemServiceImpl class, uploadFile method.-----");

		String fileName = getFileName(request);

		return null;
	}

	private String getFileName(ContentRequest request) {
		log.info("----FileSystemServiceImpl Class, getFileName method : original FileName  : {} and madeUpName : {}",
				request.getFile().getOriginalFilename(), request.getFileName());

		return new StringBuilder()
				.append(ObjectUtils.isEmpty(request.getFileName())
						? FilenameUtils.getBaseName(request.getFile().getOriginalFilename())
						: request.getFileName())
				.append(".").append(FilenameUtils.getExtension(request.getFile().getOriginalFilename())).toString();
	}

}
