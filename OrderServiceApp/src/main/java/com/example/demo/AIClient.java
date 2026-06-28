package com.example.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "openai")
public interface AIClient {

    @GetMapping("/api/ai/ask")
    String ask(@RequestParam("query") String query);
    
    @PostMapping("/api/ai/fraud-check")
    FraudResponse checkFraud(
            @RequestBody FraudCheckRequest request);

}