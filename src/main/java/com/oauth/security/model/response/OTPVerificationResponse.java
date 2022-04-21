package com.oauth.security.model.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OTPVerificationResponse implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private String message;
	private String id;

}
