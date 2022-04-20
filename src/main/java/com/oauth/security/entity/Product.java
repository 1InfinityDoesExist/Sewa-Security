package com.oauth.security.entity;

import java.util.Map;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.oauth.security.model.BaseModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document(collection = "product")
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class Product extends BaseModel {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Field("name")
	@JsonProperty("name")
	@NotEmpty(message = "Name filed must not be empty or null")
	private String name;

	private Map<String, String> roles;

}
