package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

	private final EmailService emailService;

	private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

	public KafkaConsumer(EmailService emailService) {
		this.emailService = emailService;
	}

	@KafkaListener(topics = "otp-verified-topic", groupId = "notification-group")
	public void consume(OtpVerifiedEvent event) {

		logger.info("Received OTP verified event for email: {}", event.getEmailId());
		emailService.sendWelcomeEmail(event.getEmailId());
	}

	@KafkaListener(topics = "user-event-topic", groupId = "notification-group")
	public void consume(UserEvent event) {

		logger.info("Received user event for email: {}, event type: {}", event.getEmailId(), event.getEventType());
		emailService.sendWelcomeEmail(event.getEmailId());
	}

}