package com.example.demo;

import java.time.Duration;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OTPServiceImpl implements OTPService {

	private static final Logger logger = LoggerFactory.getLogger(OTPServiceImpl.class);
	private final StringRedisTemplate redisTemplate;
	private final EmailService emailService;

	private final KafkaTemplate<String, OtpVerifiedEvent> kafkaTemplate;
	private static final int OTP_EXPIRY_MINUTES = 5;
	private static final int MAX_REQUESTS = 3;
	private static final int MAX_ATTEMPTS = 5;

	public OTPServiceImpl(StringRedisTemplate redisTemplate, EmailService emailService, KafkaTemplate<String, OtpVerifiedEvent> kafkaTemplate) {
		this.redisTemplate = redisTemplate;
		this.emailService = emailService;
		this.kafkaTemplate = kafkaTemplate;
	}

	@Override
	public void generateOTP(OTPRequest request) {

		String key = buildKey(request.getEmail(), request.getPhoneNumber());
		String rateKey = "otp:rate:" + request.getPhoneNumber();

		// 🚦 Rate Limiting
		Long count = redisTemplate.opsForValue().increment(rateKey);

		if (count == 1) {
			redisTemplate.expire(rateKey, Duration.ofMinutes(10));
		}

		if (count > MAX_REQUESTS) {
			throw new RuntimeException("Too many OTP requests. Try later.");
		}

		// 🔢 Generate OTP

		String otp = String.valueOf(new Random().nextInt(900000) + 100000);

		// 💾 Store OTP with TTL
		redisTemplate.opsForValue().set(key, otp, Duration.ofMinutes(OTP_EXPIRY_MINUTES));

		// 🔁 Initialize attempt count
		redisTemplate.opsForValue().set(key + ":attempts", "0", Duration.ofMinutes(OTP_EXPIRY_MINUTES));

		// 📧 Send Email
		logger.info("OTP is generated successfully: {}", otp);
		emailService.sendOTP(request.getEmail(), otp);
	}

	@Override
	public boolean validateOTP(OTPVerification request) {

		String key = buildKey(request.getEmail(), request.getPhoneNumber());
		String attemptKey = key + ":attempts";

		String storedOtp = redisTemplate.opsForValue().get(key);

		if (storedOtp == null) {
			throw new RuntimeException("OTP expired or not found");
		}

		// 🚦 Attempt check
		int attempts = Integer.parseInt(redisTemplate.opsForValue().get(attemptKey));

		if (attempts >= MAX_ATTEMPTS) {
			throw new RuntimeException("Max attempts reached");
		}

		// ❌ Invalid OTP
		if (!storedOtp.equals(request.getOtp())) {
			redisTemplate.opsForValue().increment(attemptKey);
			throw new RuntimeException("Invalid OTP");
		}

		// ✅ Success → delete OTP
		redisTemplate.delete(key);
		redisTemplate.delete(attemptKey);
		OtpVerifiedEvent event =
		        new OtpVerifiedEvent();

		event.setEmailId(request.getEmail());

		
		// After OTP success
		logger.info("OTP validated successfully for email: {}", request.getEmail());

		logger.info("OTP was validated succesffully", storedOtp);
		logger.info("Publishing OTP verified event");
		
		kafkaTemplate.send(
		        "otp-verified-topic",
		        event);
		return true;
	}

	private String buildKey(String email, String phone) {
		return "otp:" + email + ":" + phone;
	}
}