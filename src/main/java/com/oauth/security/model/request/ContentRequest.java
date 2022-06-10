package com.oauth.security.model.request;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class ContentRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7649643396783117774L;

	/**
	 * Get filePath
	 * 
	 * @return filePath
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("filePath")
	private String filePath;

	/**
	 * Get fileName
	 * 
	 * @return fileName
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("fileName")
	private String fileName;

	/**
	 * Get file
	 * 
	 * @return file
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("file")
	private MultipartFile file;

	/**
	 * Get tags
	 * 
	 * @return tags
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("tags")
	private List<String> tags;

	private HttpServletRequest httpServletRequest;

	/**
	 * Get description
	 * 
	 * @return description of the file.
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("description")
	private String description;

	/**
	 * Whether or not the file in the disk has to be overwrite.
	 * 
	 * @return true/false.
	 **/
	@ApiModelProperty(value = "")
	@JsonProperty("overrideFile")
	private boolean overrideFile;
}