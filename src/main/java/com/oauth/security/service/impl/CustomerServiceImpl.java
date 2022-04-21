package com.oauth.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.oauth.security.entity.Customer;
import com.oauth.security.entity.OtpDetails;
import com.oauth.security.model.request.RegistrationRequest;
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

	@Override
	public RegistrationResponse registerEmailUsingPOST(RegistrationRequest registration) {
		log.info("-----Customer Email Registration----");
		if (ObjectUtils.isEmpty(registration.getEmail())) {
			throw new RuntimeException("Email field must not be null or empty");
		}

		Customer customer = customerRepository.findCustomerByEmail(registration.getEmail());
		if (!ObjectUtils.isEmpty(customer)) {
			throw new RuntimeException(String.format("Email id : %s already in use. Please use different EmailID",
					registration.getEmail()));
		}

		OtpDetails otpDetails = otpDetailsRepository.findOtpDetailsByEmail(registration.getEmail());
		if (ObjectUtils.isEmpty(otpDetails)) {
			otpDetails = new OtpDetails();
		}

		otpDetails.setEmail(registration.getEmail());
		int otp = otpGenerator.generateOTP();
		otpDetails.setEmailOtp(otp);
		otpDetails.setEmailOtpExpirtyDate(otpExpiryTime);

		Response emailResponse = twillioEmailService.sendMail(registration.getEmail(),
				String.format("Your one time password (OTP) - %s", otp), String.format(emailBody, otp));
		if (emailResponse.getStatusCode() == 200) {
			otpDetailsRepository.save(otpDetails);
		}

		RegistrationResponse response = new RegistrationResponse();
		response.setMsg(String.format("OTP has been sent to %s ", otpDetails.getEmail()));
		return response;
	}

}
