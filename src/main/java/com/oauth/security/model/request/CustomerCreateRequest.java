package com.oauth.security.model.request;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerCreateRequest implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@NotBlank
	private String userName;
	@NotBlank
	private String id;
	private String firstName;
	private String lastName;
	@NotBlank
	private String password;
	private String profilePic;

}
