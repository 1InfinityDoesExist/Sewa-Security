package com.oauth.security.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.Column;
//import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.oauth.security.enums.HlsStatus;
//import com.oauth.security.utils.JsonToMapConverter;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Response class to be returned by Api
 * @author gaian
 *
 */
/**
 * ContentRequest
 */

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaPKMSTServerCodegen", date = "2021-07-30T10:53:55.906Z")

@Setter
@Getter
@Table(name = "content_info", indexes = { @Index(name = "content_info", columnList = "tenant_id") })
@Entity
@TypeDefs({ @TypeDef(name = "json", typeClass = JsonStringType.class),
		@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class) })

public class ContentInfo extends Auditable<String> {

	/**
	 * ID of fileName
	 * 
	 * @return id
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("id")
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "id", columnDefinition = "BINARY(16)")
	private UUID id;

	/**
	 * Id of the tenant
	 * 
	 * @return tenantId
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("tenantId")
	@Column(name = "tenant_id", nullable = false)
	private String tenantId;

	/**
	 * Id of the User
	 * 
	 * @return userId
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("userId")
	@Column(name = "user_id", nullable = false)
	private String userId;

	/**
	 * fileName
	 * 
	 * @return fileName
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("fileName")
	@Column(name = "file_name", nullable = false)
	private String fileName;

	/**
	 * fileExtension
	 * 
	 * @return fileExtension
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("fileExtension")
	@Column(name = "file_extension", nullable = false)
	private String fileExtension;

	/**
	 * filePath
	 * 
	 * @return filePath
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("filePath")
	@Column(name = "file_path", nullable = false)
	private String filePath;

	/**
	 * contentType
	 * 
	 * @return contentType
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("contentType")
	@Column(name = "content_type", nullable = false)
	private String contentType;

	/**
	 * contentLength
	 * 
	 * @return contentLength
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("contentLength")
	@Column(name = "content_length", nullable = false)
	private long contentLength;

	/**
	 * contentLength
	 * 
	 * @return contentLength
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("md5CheckSum")
	@Column(name = "md5_check_sum", nullable = false)
	private String md5CheckSum;

	/**
	 * contentLength
	 * 
	 * @return contentLength
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("isEncrypted")
	@Column(name = "is_encrypted", nullable = false, columnDefinition = "TINYINT", length = 1)
	private boolean isEncrypted;

	/**
	 * Id of the Service
	 * 
	 * @return serviceId
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("serviceId")
	@Column(name = "service_id", nullable = false)
	private String serviceId;

	/**
	 * Kafka request id
	 * 
	 * @return hlsRequestId
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("hlsRequestId")
	@Column(name = "hls_request_id")
	private String hlsRequestId;

	/**
	 * hlsUrl
	 * 
	 * @return hlsUrl
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("hlsUrl")
	@Column(name = "hls_url")
	private String hlsUrl;

	/**
	 * hlsStatus
	 * 
	 * @return hlsStatus
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("hlsStatus")
	@Column(name = "hls_status")
	private HlsStatus hlsStatus;

	/**
	 * tags
	 * 
	 * @return tags
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("tags")
	@Column(name = "tags")
	@ElementCollection(fetch = FetchType.EAGER)
	@Cascade(value = org.hibernate.annotations.CascadeType.ALL)
	private List<String> tags = new ArrayList<>();

	/**
	 * metaData
	 * 
	 * @return metaData
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("metaData")
	@Type(type = "json")
	@Column(name = "meta_data", columnDefinition = "json")
	// @Convert(attributeName = "data", converter = JsonToMapConverter.class)
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
