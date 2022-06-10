package com.oauth.security.model.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;

@lombok.Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TwitterConsoleUpdateRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4360669522089074262L;
	/**
	 * Twitter Console consumer id.
	 * 
	 * @return googleAppId
	 **/
	@ApiModelProperty(value = " TwitterConsole consumer key.", example = "205750564737721")
	@JsonProperty("consumerKey")
	@Field("consumerKey")
	@NotBlank
	private String consumerKey;

	/**
	 * twitter console consumer secret
	 * 
	 * @return googleAppSecret
	 **/
	@ApiModelProperty(value = "Twitter console consumer secret", example = "85822a90153cb1850cf9d01f8ee7a119")
	@JsonProperty("consumerSecret")
	@Field("consumerSecret")
	@NotBlank
	private String consumerSecret;

	/**
	 * twitter console consumer secret
	 * 
	 * @return googleAppSecret
	 **/
	@ApiModelProperty(value = "Twitter console accessToken", example = "85822a90153cb1850cf9d01f8ee7a119")
	@JsonProperty("accessToken")
	@Field("accessToken")
	@NotBlank
	private String accessToken;

	/**
	 * twitter console consumer secret
	 * 
	 * @return googleAppSecret
	 **/
	@ApiModelProperty(value = "Twitter console accessTokenSecret", example = "85822a90153cb1850cf9d01f8ee7a119")
	@JsonProperty("accessTokenSecret")
	@Field("accessTokenSecret")
	@NotBlank
	private String accessTokenSecret;

}