package com.example.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
    name = "otp-service",
    
    fallback = OTPClientFallback.class
)
public interface OTPClient {

    @PostMapping("/api/otp/send")
    ResponseEntity<String> sendOTP(@RequestBody OTPRequest request);

    @PostMapping("/api/otp/verify")
    ResponseEntity<String> verifyOTP(@RequestBody OTPVerification request);
}