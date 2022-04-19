package com.oauth.security.entity;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
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

@Document(collection = "customer")
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class Customer extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Field("email")
	@JsonProperty("email")
	private String email;

	@Field("mobile")
	@JsonProperty("mobile")
	private String mobile;

	@Field("firstName")
	@JsonProperty("firstName")
	private String firstName;

	@Field("lastName")
	@JsonProperty("lastName")
	private String lastName;

	@Field("password")
	@JsonProperty("password")
	private String password;

	@Field("profilePic")
	@JsonProperty("profilePic")
	private String profilePic;

	@Field("products")
	@JsonProperty("products")
	private List<String> products;

	private List<String> roles;

	@Field("walletDetails")
	@JsonProperty("walletDetails")
	@DBRef
	private List<WalletDetails> walletDetials;

}
