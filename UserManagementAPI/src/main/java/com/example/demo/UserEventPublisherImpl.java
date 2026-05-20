package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserEventPublisherImpl implements UserEventPublisher {

	private static final Logger logger = LoggerFactory.getLogger(UserEventPublisherImpl.class);

	private final KafkaTemplate<String, Object> kafkaTemplate;

	@Value("${app.kafka.topic.user-events}")
	private String userEventsTopic;

	public UserEventPublisherImpl(KafkaTemplate<String, Object> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	@Override
	public void publishUserCreatedEvent(User user) {

		UserEvent event = new UserEvent(
				"USER_REGISTERED",
				user.getUserId(),
				user.getEmail(),
				user.getMobileNumber()
		);

		kafkaTemplate.send(userEventsTopic, event);

		logger.info("Published USER_REGISTERED event for user ID: {}", user.getUserId());
	}

	@Override
	public void publishUserDeletedEvent(User user) {

		UserEvent event = new UserEvent(
				"USER_DELETED",
				user.getUserId(),
				user.getEmail(),
				user.getMobileNumber()
		);

		kafkaTemplate.send(userEventsTopic, event);

		logger.info("Published USER_DELETED event for user ID: {}", user.getUserId());
	}

	// ✅ NEW METHOD (MOST IMPORTANT)
	public void publishUserVerifiedEvent(User user) {

		UserEvent event = new UserEvent(
				"USER_VERIFIED",
				user.getUserId(),
				user.getEmail(),
				user.getMobileNumber()
		);

		kafkaTemplate.send(userEventsTopic, event);

		logger.info("Published USER_VERIFIED event for user ID: {}", user.getUserId());
	}
}