package com.oauth.security.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.oauth.security.model.response.ContentResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaPKMSTServerCodegen", date = "2021-06-07T09:54:05.519Z")
@Api(value = "FileSystemController", description = "File System Crud Apis.")
public interface FileSystemController {

	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created", response = ContentResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 200, message = "OK") })
	@RequestMapping(value = "/v1.0/file-system/upload", consumes = { "multipart/form-data" }, produces = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<ContentResponse> uploadContentUsingPOST(
			@ApiParam(value = "file detail") @RequestPart("file") MultipartFile file,
			@ApiParam(value = "file path") @RequestParam(value = "filePath", required = false) String filePath,
			@ApiParam(value = "tags") @RequestParam(value = "tags", required = false) List<String> tags,
			@ApiParam(value = "file name without extension") @RequestParam(value = "fileName", required = false) String fileName,
			@ApiParam(value = "description") @RequestParam(value = "description", required = false) String description,
			@ApiParam(value = "overrideFile") @RequestParam(value = "overrideFile", defaultValue = "false") Boolean overrideFile,
			HttpServletRequest httpServletRequest) throws IOException;

}
