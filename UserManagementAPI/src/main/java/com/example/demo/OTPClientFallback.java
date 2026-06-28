package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class OTPClientFallback implements OTPClient {

    @Override
    public ResponseEntity<String> sendOTP(OTPRequest request) {
        return ResponseEntity
                .internalServerError()
                .body("OTP Service is down. Please try later.");
    }

    @Override
    public ResponseEntity<String> verifyOTP(OTPVerification request) {
        return ResponseEntity
                .internalServerError()
                .body("OTP verification failed. Service unavailable.");
    }
}