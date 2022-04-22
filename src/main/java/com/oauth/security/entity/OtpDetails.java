package com.oauth.security.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Document(collection = "otp_details")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OtpDetails implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;

	@Indexed
	private String email;
	private String mobile;
	private int emailOtp;
	private int mobileOtp;
	private String product;

	private Date emailOtpExpirtyDate;

	private Date mobileOtpExpiryDate;

	public Date getEmailOtpExpirtyDate() {
		return emailOtpExpirtyDate;
	}

	public void setEmailOtpExpirtyDate(Date emailOtpExpirtyDate) {
		this.emailOtpExpirtyDate = emailOtpExpirtyDate;
	}

	public void setEmailOtpExpirtyDate(int minutes) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, minutes);
		this.emailOtpExpirtyDate = now.getTime();
	}

	public Date getMobileOtpExpiryDate() {
		return mobileOtpExpiryDate;
	}

	public void setMobileOtpExpiryDate(Date mobileOtpExpiryDate) {
		this.mobileOtpExpiryDate = mobileOtpExpiryDate;
	}

	public void setMobileOtpExpiryDate(int minutes) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, minutes);
		this.mobileOtpExpiryDate = now.getTime();
	}

	public boolean isEmailOtpExpired() {
		return new Date().after(this.emailOtpExpirtyDate);
	}

	public boolean isMobileOtpExpired() {
		return new Date().after(this.mobileOtpExpiryDate);
	}
}
