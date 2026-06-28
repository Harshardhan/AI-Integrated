package com.example.demo;

import java.math.BigDecimal;

import org.slf4j.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AIService {

	private static final Logger logger = LoggerFactory.getLogger(AIService.class);

	private final ObjectMapper objectMapper = new ObjectMapper();

	private static final String SYSTEM_PROMPT = """
			You are a Senior Java Architect and Spring Boot expert.

			Rules:
			- Give practical answers.
			- Use real-world examples.
			- Be technically accurate.
			- Keep responses concise.
			""";

	private final ChatClient chatClient;

	public AIService(ChatClient.Builder builder) {
		this.chatClient = builder.build();
	}

	public String ask(String topic) {

		String template = """
				Topic: {topic}

				Format the response exactly as:

				## Definition

				## Real World Example

				## Production Usage

				## Interview Questions

				Requirements:
				- Use Java and Spring Boot examples
				- Keep response under 300 words
				""";

		return executePrompt(topic, template);
	}

	public String interview(String topic) {

		String template = """
				Topic: {topic}

				Generate 10 interview questions with concise answers.

				Format:

				Q1:
				Answer:

				Q2:
				Answer:

				Requirements:
				- Focus on {topic}
				- Use production examples where applicable
				- Keep answers concise
				""";

		return executePrompt(topic, template);
	}

	private String executePrompt(String topic, String template) {

		long startTime = System.currentTimeMillis();

		try {

			logger.info("Sending request to AI. Topic={}", topic);

			String response = chatClient.prompt().system(SYSTEM_PROMPT)
					.user(user -> user.text(template).param("topic", topic)).call().content();

			logger.info("AI response received in {} ms", System.currentTimeMillis() - startTime);

			return response;

		} catch (Exception e) {

			logger.error("AI request failed", e);

			return "Unable to process AI request: " + e.getMessage();
		}
	}

	public FraudResponse analyzeFraudRisk(FraudCheckRequest request) {

		long startTime = System.currentTimeMillis();

		try {

			logger.info("Analyzing fraud risk for order amount={}", request.getOrderAmount());

			String template = """
					You are a fraud analyst.

					The fraud score has already been calculated.

					Risk Score : {riskScore}

					Risk Level : {riskLevel}

					Customer Age : {customerAge}

					Previous Orders : {previousOrders}

					Order Amount : {orderAmount}

					Explain:

					1. Why this score was assigned.
					2. Give one recommendation.

					Return ONLY JSON.

					{
					  "reason":"",
					  "recommendation":""
					}				""";
			String response = chatClient.prompt().system("""
					You are a Fraud Detection Expert.
					Return ONLY valid JSON.
					""").user(user -> user.text(template)

					.param("riskScore", String.valueOf(request.getRiskScore()))

					.param("riskLevel", request.getFraudRiskLevel())

					.param("customerAge", String.valueOf(request.getCustomerAge()))

					.param("previousOrders", String.valueOf(request.getPreviousOrders()))

					.param("orderAmount", request.getOrderAmount().toPlainString())).call().content();
			logger.info("Fraud analysis completed in {} ms", System.currentTimeMillis() - startTime);
			logger.info("AI Response:\n{}", response);
			return convertToFraudResponse(response);

		} catch (Exception e) {

			logger.error("Fraud analysis failed", e);

			return new FraudResponse("UNKNOWN", "Unable to analyze risk");
		}
	}

	private FraudResponse convertToFraudResponse(String response) {

		try {

			String cleaned = response.replace("```json", "").replace("```", "").trim();

			logger.info("Cleaned JSON:\n{}", cleaned);

			return objectMapper.readValue(cleaned, FraudResponse.class);

		} catch (Exception e) {

			logger.error("Failed to parse AI response", e);

			return new FraudResponse("UNKNOWN", "Unable to analyze risk");
		}
	}
}