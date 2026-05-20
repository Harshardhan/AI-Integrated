package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserEventConsumer {

	private static final Logger logger = LoggerFactory.getLogger(UserEventConsumer.class);

	private final NotificationService notificationService;

	public UserEventConsumer(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	@KafkaListener(topics = "${app.kafka.topic.user-events}", groupId = "notification-group", containerFactory = "userEventListenerFactory" // ✅
																																			// FIXED
	)
	public void consume(UserEvent event) {

		logger.info("Received event: {}", event.getEventType());

		try {

			switch (event.getEventType()) {

			case "USER_REGISTERED":
				notificationService.sendOtpEmail(event);
				break;

			case "USER_VERIFIED":
				notificationService.sendWelcomeEmail(event);
				break;

			default:
				logger.warn("Unknown event type: {}", event.getEventType());
			}

		} catch (Exception e) {
			logger.error("Error processing event: {}", event, e);
			throw e; // 🔥 important for retry
		}
	}

}