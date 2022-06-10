package com.oauth.security.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaPKMSTServerCodegen", date = "2021-07-30T10:53:55.906Z")

@Document(collection = "content_info")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
public class ContentInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ID of fileName
	 * 
	 * @return id
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("id")
	@Id
	private UUID id;

	/**
	 * Id of the tenant
	 * 
	 * @return tenantId
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("tenantId")
	private String tenantId;

	/**
	 * Id of the User
	 * 
	 * @return userId
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("userId")
	private String userId;

	/**
	 * fileName
	 * 
	 * @return fileName
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("fileName")
	private String fileName;

	/**
	 * fileExtension
	 * 
	 * @return fileExtension
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("fileExtension")
	private String fileExtension;

	/**
	 * filePath
	 * 
	 * @return filePath
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("filePath")
	private String filePath;

	/**
	 * contentType
	 * 
	 * @return contentType
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("contentType")
	private String contentType;

	/**
	 * contentLength
	 * 
	 * @return contentLength
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("contentLength")
	private long contentLength;

	/**
	 * contentLength
	 * 
	 * @return contentLength
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("md5CheckSum")
	private String md5CheckSum;

	/**
	 * contentLength
	 * 
	 * @return contentLength
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("isEncrypted")
	private boolean isEncrypted;

	/**
	 * Id of the Service
	 * 
	 * @return serviceId
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("serviceId")
	private String serviceId;

	/**
	 * Kafka request id
	 * 
	 * @return hlsRequestId
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("hlsRequestId")
	private String hlsRequestId;

	/**
	 * hlsUrl
	 * 
	 * @return hlsUrl
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("hlsUrl")
	private String hlsUrl;

	/**
	 * tags
	 * 
	 * @return tags
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("tags")
	private List<String> tags = new ArrayList<>();

	/**
	 * metaData
	 * 
	 * @return metaData
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("metaData")
	private Map<String, Object> metaData = new HashMap<>();

	/**
	 * Get description
	 * 
	 * @return description of the file.
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("description")
	private String description;

}