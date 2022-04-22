package com.oauth.security.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseModel implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@JsonProperty("createdDate")
	@Field("createdDate")
	private String createdDate;

	@JsonProperty("updatedDate")
	@Field("updatedDate")
	private String updatedDate;

	@JsonProperty("createdBy")
	@Field("createdBy")
	private String createdBy;

	@JsonProperty("updatedBy")
	@Field("updatedBy")
	private String updatedBy;

	@JsonProperty("isActive")
	@Field("isActive")
	private boolean isActive;

}
