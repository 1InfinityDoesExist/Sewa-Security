package com.oauth.security.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaPKMSTServerCodegen", date = "2021-07-30T10:53:55.906Z")

@Setter
@Getter
public class ContentMetadata implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1692513832732536610L;

	/**
	 * Get name
	 * 
	 * @return name
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("name")
	private String name;

	/**
	 * Get contentType
	 * 
	 * @return contentType
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("contentType")
	private String contentType;

	/**
	 * Get length
	 * 
	 * @return length
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("length")
	private long length;

	/**
	 * Get tenantId
	 * 
	 * @return tenantId
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("tenantId")
	private String tenantId;

	/**
	 * Get serviceId
	 * 
	 * @return serviceId
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("serviceId")
	private String serviceId;

	/**
	 * Get userId
	 * 
	 * @return userId
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("userId")
	private String userId;

	/**
	 * Get metadata
	 * 
	 * @return metadata
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("metadata")
	private Map<String, Object> metadata;

	/**
	 * Get createdOn
	 * 
	 * @return createdOn
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("createdOn")
	private Long createdOn;

	/**
	 * Get tags
	 * 
	 * @return tags
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("tags")
	private List<String> tags;
}