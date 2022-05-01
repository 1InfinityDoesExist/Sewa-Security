package com.oauth.security.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerUpdateRequest {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	private String firstName;
	private String lastName;

}
