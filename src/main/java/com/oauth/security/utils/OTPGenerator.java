package com.oauth.security.utils;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Value;

public class OTPGenerator {

	/* Length of OTP is Always 6 */

	@Value("${otp.start.range}")
	public int otpStartRange;

	@Value("${otp.end.range}")
	public int otpEndRange;

	public int generateOTP() {
		return otpStartRange + new Random().nextInt(otpEndRange);
	}

	public String randomOTPGeneration() {
		int random = ThreadLocalRandom.current().nextInt(0, 10000);
		return String.format("%04d", random);
	}

}
