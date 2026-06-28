package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.util.backoff.FixedBackOff;

@EnableKafka
@Configuration
public class KafkaConfig {

	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;

	private static final Logger logger = LoggerFactory.getLogger(KafkaConfig.class);

	// ----------------CONSUMER CONFIG------------------

	@Bean
	public ConsumerFactory<String, UserEvent> userEventConsumerFactory() {

		JsonDeserializer<UserEvent> deserializer = new JsonDeserializer<>(UserEvent.class);
		deserializer.addTrustedPackages("*");

		Map<String, Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "notification-group");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

		logger.info("Kafka Consumer configured with bootstrap servers: {}", bootstrapServers);

		return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), deserializer);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, UserEvent> userEventListenerFactory() {

		ConcurrentKafkaListenerContainerFactory<String, UserEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();

		factory.setConsumerFactory(userEventConsumerFactory());

		// 🔥 Retry mechanism
		factory.setCommonErrorHandler(new DefaultErrorHandler(new FixedBackOff(1000L, 3))); // Retry every 1 second, up
																							// to 3 times

		return factory;
	}

	
	
	@Bean
	public ConsumerFactory<String, OtpVerifiedEvent> otpVerifiedEventConsumerFactory() {

		JsonDeserializer<OtpVerifiedEvent> deserializer = new JsonDeserializer<>(OtpVerifiedEvent.class);
		deserializer.addTrustedPackages("*");

		Map<String, Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "notification-group");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

		logger.info("Kafka Consumer configured with bootstrap servers: {}", bootstrapServers);

		return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), deserializer);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, OtpVerifiedEvent> otpVerifiedEventListenerFactory() {

		ConcurrentKafkaListenerContainerFactory<String, OtpVerifiedEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();

		factory.setConsumerFactory(otpVerifiedEventConsumerFactory());

		// 🔥 Retry mechanism
		factory.setCommonErrorHandler(new DefaultErrorHandler(new FixedBackOff(1000L, 3))); // Retry every 1 second, up
																							// to 3 times

		return factory;
	}

	
}