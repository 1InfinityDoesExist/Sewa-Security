package com.oauth.security.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.oauth.security.entity.Customer;
import com.oauth.security.entity.OtpDetails;
import com.oauth.security.model.request.OTPVerificationRequest;
import com.oauth.security.model.request.RegistrationRequest;
import com.oauth.security.model.response.OTPVerificationResponse;
import com.oauth.security.model.response.RegistrationResponse;
import com.oauth.security.repository.CustomerRepository;
import com.oauth.security.repository.OtpDetailsRepository;
import com.oauth.security.service.CustomerService;
import com.oauth.security.service.email.TwillioEmailService;
import com.oauth.security.utils.OTPGenerator;
import com.sendgrid.Response;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private OtpDetailsRepository otpDetailsRepository;

	@Autowired
	private OTPGenerator otpGenerator;

	@Autowired
	private TwillioEmailService twillioEmailService;

	@Value("${otp.expiry:15}")
	private int otpExpiryTime;

	@Value("${email.verification.body}")
	private String emailBody;

	/**
	 * 
	 */
	@Override
	public RegistrationResponse registerEmailUsingPOST(RegistrationRequest registration) {
		log.info("-----Customer Email Registration----");
		if (ObjectUtils.isEmpty(registration.getEmail())) {
			throw new RuntimeException("Email field must not be null or empty");
		}

		Customer customer = customerRepository.findCustomerByEmailAndProduct(registration.getEmail(),
				registration.getProduct());
		if (!ObjectUtils.isEmpty(customer)) {
			throw new RuntimeException(String.format("Email id : %s already in use. Please use different EmailID",
					registration.getEmail()));
		}

		OtpDetails otpDetails = otpDetailsRepository.findOtpDetailsByEmailAndProduct(registration.getEmail(),
				registration.getProduct());
		if (ObjectUtils.isEmpty(otpDetails)) {
			otpDetails = new OtpDetails();
		}

		otpDetails.setEmail(registration.getEmail());
		int otp = otpGenerator.generateOTP();
		otpDetails.setEmailOtp(otp);
		otpDetails.setProduct(registration.getProduct());
		otpDetails.setEmailOtpExpirtyDate(otpExpiryTime);

		Response emailResponse = twillioEmailService.sendMail(registration.getEmail(),
				String.format("Your one time password (OTP) - %s", otp), String.format(emailBody, otp));
		log.info("------Response from twillio email : {}", emailResponse.getBody());

		RegistrationResponse response = new RegistrationResponse();

		if (emailResponse.getStatusCode() == 202) {
			otpDetailsRepository.save(otpDetails);
			log.info("-----OtpDetails ID : {}", otpDetails.getId());
			response.setMsg(String.format("OTP has been sent to %s ", otpDetails.getEmail()));
		} else {
			response.setMsg("Twillio server issue.");
		}
		return response;
	}

	/**
	 * 
	 */
	@Override
	public OTPVerificationResponse verifyConsumerEmailUsingPOST(OTPVerificationRequest otpVerificationRequest) {
		log.info("-----OTPVerificationRequest class, verifyConsumerEmailUsingPOST method -----");
		if (ObjectUtils.isEmpty(otpVerificationRequest.getEmail())) {
			throw new RuntimeException("Email field must not be null or empty");
		}

		OTPVerificationResponse response = new OTPVerificationResponse();

		OtpDetails otpDetails = otpDetailsRepository.findOtpDetailsByEmailAndProduct(otpVerificationRequest.getEmail(),
				otpVerificationRequest.getProduct());
		if (ObjectUtils.isEmpty(otpDetails) || otpVerificationRequest.getEmailOtp() != otpDetails.getEmailOtp()) {
			throw new RuntimeException("Incorrect otp");
		} else {
			if (otpDetails.isEmailOtpExpired()) {
				throw new RuntimeException("OTP has been expired. Please request for a new OTP");
			} else {
				otpDetailsRepository.delete(otpDetails);

				Customer customer = new Customer();
				customer.setActive(true);
				customer.setEmail(otpVerificationRequest.getEmail());
				customer.setCreatedDate(new Date().toString());
				customer.setProduct(otpVerificationRequest.getProduct());
				customer.setUpdatedDate(customer.getCreatedDate());

				customerRepository.save(customer);

				log.info("----CustomerId : {}", customer.getId());
				response.setId(customer.getId());
				response.setMessage("Congrulation. You have successfully verified you emailID");

			}
		}
		return response;

	}
}
