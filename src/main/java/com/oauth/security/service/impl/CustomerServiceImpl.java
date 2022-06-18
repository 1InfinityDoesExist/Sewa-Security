package com.oauth.security.service.impl;

import java.util.Date;

import javax.validation.Valid;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oauth.security.entity.Customer;
import com.oauth.security.entity.OtpDetails;
import com.oauth.security.entity.Product;
import com.oauth.security.model.request.CustomerCreateRequest;
import com.oauth.security.model.request.CustomerUpdateRequest;
import com.oauth.security.model.request.OTPVerificationRequest;
import com.oauth.security.model.request.RegistrationRequest;
import com.oauth.security.model.response.CustomerResponse;
import com.oauth.security.model.response.OTPVerificationResponse;
import com.oauth.security.model.response.RegistrationResponse;
import com.oauth.security.repository.mongo.CustomerRepository;
import com.oauth.security.repository.mongo.OtpDetailsRepository;
import com.oauth.security.repository.mongo.ProductRepository;
import com.oauth.security.service.CustomerService;
import com.oauth.security.service.email.TwillioEmailService;
import com.oauth.security.service.sms.TwillioSMSService;
import com.oauth.security.utils.OTPGenerator;
import com.sendgrid.Response;

import lombok.extern.slf4j.Slf4j;

@CacheConfig(cacheNames = { "customer" })
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

	@Autowired
	private TwillioSMSService twillioSMSService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CacheManager cacheManager;

	@Value("${otp.expiry:15}")
	private int otpExpiryTime;

	@Value("${email.verification.body}")
	private String emailBody;

	private static final ObjectMapper objectMapper;

	static {
		objectMapper = new ObjectMapper();
	}

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

	@Override
	public CustomerResponse registerCustomerUsingPOST(CustomerCreateRequest customerCreateRequest) {
		log.info("-----Persisting customer credentials into database");

		Customer customer = customerRepository.findById(customerCreateRequest.getId())
				.orElseThrow(() -> new RuntimeException("Customer does not exist "));

		log.info("-----Customer with id : {} is {}", customerCreateRequest.getId(), customer);

		if (ObjectUtils.isEmpty(customerRepository.findCustomerByUserNameAndProduct(customerCreateRequest.getUserName(),
				customer.getProduct()))) {
			customer.setFirstName(customerCreateRequest.getFirstName());
			customer.setLastName(customerCreateRequest.getLastName());
			// customer.setRoles(getProductRoles(customer.getProduct()));
			customer.setRoles("USER");
			customer.setProfilePic(customerCreateRequest.getProfilePic());
			customer.setPassword(passwordEncoder.encode(customerCreateRequest.getPassword()));

			customerRepository.save(customer);

			CustomerResponse response = new CustomerResponse();
			response.setMessage("Customer registered successfully.");

			return response;

		} else {
			throw new RuntimeException(
					String.format("Customer with username %s already exist.", customerCreateRequest.getUserName()));
		}

	}

	private String getProductRoles(String product) {
		log.info("-----Method to find roles by product.");

		Product prod = productRepository.findProductByNameAndIsActive(product, true);
		if (ObjectUtils.isEmpty(prod)) {
			throw new RuntimeException("Product does not exit.");
		}
		return prod.getRoles().get("CUSTOMER");

	}

	/**
	 * @Cacheable(value = "customer", key = "#id", sync = true)
	 * 
	 * @Transactional(readOnly = true)
	 */
	@Override
	public Customer retrieveCustomerUsingGET(String id) {
		log.info("-----CustomerServiceImpl class, retrieveCustomerUsingGET method , Id : {}", id);
		Customer customer = null;
		boolean isCacheNull = true;

		Cache cache = cacheManager.getCache("customer");
		if (!ObjectUtils.isEmpty(cache)) {
			isCacheNull = false;
			customer = cache.get(id + "_false", Customer.class);
			log.info("----Customer from cache : {}", customer);
			if (!ObjectUtils.isEmpty(customer)) {
				return customer;
			}
		}
		customer = customerRepository.findCustomerByIdAndIsActive(id, true);
		if (!ObjectUtils.isEmpty(customer) && !isCacheNull) {
			cache.put(id + "_false", customer);
			return customer;
		}
		throw new RuntimeException(String.format("Customer with id %s does not exist", id));
	}

	@Override
	public Page<Customer> retrieveAllCustomerUsingGET(Pageable pageable) {
		log.info("-----CustomerServiceImpl class, retrieveCustomerUsingGET method.-----");
		Page<Customer> customers = customerRepository.findCustomerByIsActive(true, pageable);
		log.info("-----Customers : {}", customers.getNumberOfElements());
		return customers;

	}

	@Override
	public void deleteCustomerUsingDELETE(String id) {
		log.info("-----CustomerServiceImpl class, deleteCustomerUsingDELETE method.-----");
		Customer customer = retrieveCustomerUsingGET(id);
		customer.setActive(false);
		customerRepository.save(customer);
		return;
	}

	@Override
	public CustomerResponse updateCustomerUsingPUT(String id, @Valid CustomerUpdateRequest customerUpdateRequest)
			throws JsonProcessingException, ParseException {
		log.info("-----CustomerServiceImpl class, deleteCustomerUsingDELETE method.-----");

		Customer customer = retrieveCustomerUsingGET(id);

		JSONObject payloadObject = (JSONObject) new JSONParser()
				.parse(objectMapper.writeValueAsString(customerUpdateRequest));

		JSONObject dbCustomerObject = (JSONObject) new JSONParser().parse(objectMapper.writeValueAsString(customer));

		for (Object obj : payloadObject.keySet()) {
			String param = (String) obj;
			dbCustomerObject.put(param, payloadObject.get(param));
		}

		customerRepository.save(objectMapper.readValue(dbCustomerObject.toJSONString(), Customer.class));

		CustomerResponse response = new CustomerResponse();
		response.setMessage("Customer update successfully.");

		return response;

	}

	@Override
	public RegistrationResponse updateMobileUsingPOST(RegistrationRequest registration) {
		log.info("-----CustomerServiceImpl class, updateMobileUsingPOST method.-----");
		if (ObjectUtils.isEmpty(registration.getEmail())) {
			throw new RuntimeException("----Moible number must not be null or empty");
		}

		Customer customer = customerRepository.findCustomerByMobileAndProduct(registration.getMobile(),
				registration.getProduct());

		log.info("----Customer from db : {}", customer);

		if (!ObjectUtils.isEmpty(customer)) {
			throw new RuntimeException(
					String.format("Customer with mobile %s already exist", registration.getMobile()));
		}

		OtpDetails otpDetails = otpDetailsRepository.findOtpDetailsByMobileAndProduct(registration.getMobile(),
				registration.getProduct());
		if (ObjectUtils.isEmpty(otpDetails)) {
			otpDetails = new OtpDetails();
		}

		otpDetails.setMobile(registration.getMobile());
		int otp = otpGenerator.generateOTP();
		otpDetails.setMobileOtp(otp);
		otpDetails.setProduct(registration.getProduct());
		otpDetails.setMobileOtpExpiryDate(otpExpiryTime);

		log.info("----Sending otp to the registered mobile number----");
		twillioSMSService.sendSMS(registration.getMobile(), String.format("Your one time password (OTP) - %s", otp));

		otpDetailsRepository.save(otpDetails);

		RegistrationResponse response = new RegistrationResponse();
		response.setMsg(String.format("OTP has been sent to  %s", registration.getMobile()));
		return response;

	}

	@Override
	public OTPVerificationResponse verifyMobileOTPUsingPOST(OTPVerificationRequest otpVerificationRequest) {
		log.info("-----CustomerServiceImpl class, updateMobileUsingPOST method.-----");

		OtpDetails otpDetails = otpDetailsRepository.findOtpDetailsByMobileAndProduct(
				otpVerificationRequest.getMobile(), otpVerificationRequest.getProduct());
		if (ObjectUtils.isEmpty(otpDetails)) {
			throw new RuntimeException("Invalid otp");
		}

		if (otpDetails.getMobileOtp() == otpVerificationRequest.getMobileOtp()) {
			if (otpDetails.isMobileOtpExpired()) {
				throw new RuntimeException("OPT has been expired. Please request for a new one.");
			}
			otpDetailsRepository.delete(otpDetails);
			Customer customer = customerRepository.findCustomerByEmailAndProduct(otpVerificationRequest.getEmail(),
					otpVerificationRequest.getProduct());
			customer.setMobile(otpVerificationRequest.getMobile());
			customerRepository.save(customer);
			OTPVerificationResponse response = new OTPVerificationResponse();
			response.setMessage("Successfully updated the mobile number");
			return response;
		}
		throw new RuntimeException("OTP is incorrect. Please check the otp and try again");
	}
}
