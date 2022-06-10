package com.oauth.security.model.response;

import java.util.UUID;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.oauth.security.model.ContentMetadata;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ContentResponse {

	/**
	 * Get id
	 * 
	 * @return id
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("id")
	private UUID id;

	/**
	 * Get status
	 * 
	 * @return status
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("status")
	private HttpStatus status;

	/**
	 * Get msg
	 * 
	 * @return msg
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("msg")
	private String msg;

	/**
	 * Get url
	 * 
	 * @return url
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("url")
	private String url;

	/**
	 * Get downloadUrl
	 * 
	 * @return downloadUrl
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("downloadUrl")
	private String downloadUrl;

	/**
	 * Get info
	 * 
	 * @return info
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("info")
	private ContentMetadata info;

	/**
	 * Get description
	 * 
	 * @return description of the file.
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("description")
	private String description;

	public ContentResponse(UUID id, HttpStatus status, String msg, String url, ContentMetadata info) {
		this.id = id;
		this.status = status;
		this.msg = msg;
		this.url = url;
		this.info = info;
	}

	public ContentResponse(UUID id, HttpStatus status, String msg, String url, String downloadUrl,
			ContentMetadata info) {
		this.id = id;
		this.status = status;
		this.msg = msg;
		this.url = url;
		this.downloadUrl = downloadUrl;
		this.info = info;
	}

	public ContentResponse(UUID id, HttpStatus status, String msg, String url, String downloadUrl, ContentMetadata info,
			String description) {
		this.id = id;
		this.status = status;
		this.msg = msg;
		this.url = url;
		this.downloadUrl = downloadUrl;
		this.info = info;
		this.description = description;
	}

	public ContentResponse() {
	}
}