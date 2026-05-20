package com.example.demo;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AIService {

    private final ChatClient chatClient;

    public AIService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String ask(String prompt) {
        System.out.println(">>> Sending: " + prompt);

        String response = chatClient.prompt()
                .user(prompt)
                .call()
                .content();

        System.out.println("<<< Response: " + response);

        return response;
    }}