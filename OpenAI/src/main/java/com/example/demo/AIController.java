package com.example.demo;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/ai")
public class AIController {

	private final AIService aiService;

	public AIController(AIService aiService) {
		this.aiService = aiService;
	}

	@GetMapping("/ask")
	public String ask(@RequestParam String query) {
		return aiService.ask(query);
	}

	@GetMapping("/interview")
	public String interview(@RequestParam String topic) {
		return aiService.interview(topic);
	}

	@PostMapping("/fraud-check")
	public FraudResponse checkFraud(@RequestBody FraudCheckRequest request) {

		return aiService.analyzeFraudRisk(request);
	}

	@PostMapping("/rag")
	public RagResponse rag(@RequestBody RagRequest request) {
		return aiService.rag(request);
	}
	
	@PostMapping("/chat")
	public String chat(@RequestBody String request) {
		return aiService.chat(request);
	}

}