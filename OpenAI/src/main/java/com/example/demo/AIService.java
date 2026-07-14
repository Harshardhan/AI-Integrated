package com.example.demo;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

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

	public String ask(String request) {

		logger.info("Received ask request: {}", request);
		String template = """
				Question:
				{question}

				Please answer the question using the following format:

				1. Definition
				2. Architecture
				3. Working
				4. Real-world Example
				5. Spring Boot Example (if applicable)
				6. Best Practices
				7. Common Mistakes
				8. Interview Questions

				Rules:

				- Maximum 200 words
				- Use simple English
				- Include a simple ASCII diagram when applicable
				- Use Java 21
				- Use Spring Boot 3.x examples
				- Do not invent APIs or libraries.
				""";
		String response = chatClient.prompt().system(SYSTEM_PROMPT).

				user(user -> user.text(template).param("question", request)).call().content();
		logger.debug("AI Response: {}", response);
		return response;

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
				- keep under 200 words
				""";

		logger.info("Received interview request for topic: {}", topic);
		return executePrompt(topic, template);
	}

	private String executePrompt(String request, String template) {
		long startTime = System.currentTimeMillis();

		try {

			logger.info("Sending request to AI. Topic={}", request);

			String response = chatClient.prompt().system(SYSTEM_PROMPT)
					.user(user -> user.text(template).param("topic", request)).call().content();
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
					You are an AI Fraud Detection System.

					Analyze the following order.
										The fraud score has already been calculated.

					Customer Name:
					Location:
					Order Amount:
					Payment Method:
					Items:

					Return only JSON.

					{
					  "riskLevel":"",
					  "reason":""
					}

										Risk Score: {riskScore}

										Risk Level: {riskLevel}

										Customer Age: {customerAge}

										Previous Orders: {previousOrders}

										Order Amount: {orderAmount}

										Explain:

										1. Why this score was assigned.
										2. Give one recommendation.

										Return ONLY valid JSON.

										The JSON must contain exactly these fields:

										- reason
										- recommendation

										Do NOT return markdown.
										Do NOT use code fences.
										Do NOT add any extra text.
										""";

			String response = chatClient.prompt().system("""
					You are a Fraud Detection Expert.
					Return ONLY valid JSON.
					""")
					.user(user -> user.text(template).param("riskScore", String.valueOf(request.getRiskScore()))
							.param("riskLevel", request.getFraudRiskLevel())
							.param("customerAge", String.valueOf(request.getCustomerAge()))
							.param("previousOrders", String.valueOf(request.getPreviousOrders()))
							.param("orderAmount", request.getOrderAmount().toPlainString()))
					.call().content();

			logger.info("Fraud analysis completed in {} ms", System.currentTimeMillis() - startTime);

			logger.info("AI Response: {}", response);

			return convertToFraudResponse(response);

		} catch (Exception e) {

			logger.error("Fraud analysis failed", e);

			return new FraudResponse("Unable to determine fraud reason.", "Manual review recommended.");
		}
	}

	private FraudResponse convertToFraudResponse(String response) {

		try {

			String cleaned = response.replace("```json", "").replace("```", "").trim();

			logger.info("Cleaned JSON:\n{}", cleaned);

			return objectMapper.readValue(cleaned, FraudResponse.class);

		} catch (Exception e) {

			logger.error("Failed to parse AI response", e);

			return new FraudResponse("Unable to parse AI response.", "Manual review recommended.");
		}
	}

	public RagResponse rag(RagRequest request) {

		String prompt = """
				You are an HR policy assistant.

				Use ONLY the provided context.

				If the answer is not present in the context,
				reply exactly:

				I don't know.

				Keep the answer concise.

				Context:
				%s

				Question:
				%s
				""".formatted(request.getContext(), request.getQuestion());
		logger.info("=========== PROMPT ===========");
		logger.info(prompt);
		logger.info("==============================");
		String answer = chatClient.prompt().system("""
				    You are an HR assistant.

				    Use ONLY the supplied context.

				    If the answer is missing,
				    reply exactly:

				    I don't know.
				""").user(prompt).call().content();
		return new RagResponse(answer);
	}
	
	public String chat(String userMessage) {
		String prompt = """
				You are a helpful assistant.

				User: %s
				""".formatted(userMessage);
		logger.info("=========== PROMPT ===========");
		logger.info(prompt);
		logger.info("==============================");
		String answer = chatClient.prompt().system("""
				    You are a helpful assistant.
				""").user(prompt).call().content();
		return answer;
	}
}