package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderEventPublisherImpl implements OrderEventPublisher {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderEventPublisherImpl.class);
	private final KafkaTemplate<String, Object> kafkaTemplate;

	public OrderEventPublisherImpl(KafkaTemplate<String, Object> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}
	
	@Value("${app.kafka.topic.order-created}")
	private String ordersTopic;

	@Override
	public void publishOrderCreatedEvent(Order order) {
		kafkaTemplate.send(ordersTopic, order.getId().toString());
		logger.info("Published OrderCreated event for order ID: {}", order.getId());
	}

}
