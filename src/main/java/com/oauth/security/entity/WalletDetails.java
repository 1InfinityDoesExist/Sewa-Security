package com.oauth.security.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document(collection = "wallet_details")
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class WalletDetails {

	@JsonProperty("id")
	@Field("id")
	@Id
	private String id;

	@JsonProperty("amount")
	@Field("amount")
	private Double amount;

	@JsonProperty("isActive")
	@Field("isActive")
	private boolean isActive;

	@JsonProperty("email")
	@Field("email")
	private String email;

	@JsonProperty("mobile")
	@Field("mobile")
	private String mobile;
}
