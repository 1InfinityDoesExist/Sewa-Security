package com.oauth.security.entity;

import java.io.Serializable;
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
public class Customer extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Field("userName")
	@JsonProperty("userName")
	private String userName;

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

	@Field("product")
	@JsonProperty("product")
	private String product;

	private String roles;

	@Field("walletDetails")
	@JsonProperty("walletDetails")
	@DBRef
	private List<WalletDetails> walletDetials;

}
