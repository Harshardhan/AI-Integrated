package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/otp")
public class OTPController {

    private final OTPService otpService;

    public OTPController(OTPService otpService) {
        this.otpService = otpService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendOTP(@RequestBody OTPRequest request) {
        otpService.generateOTP(request);
        return ResponseEntity.ok("OTP sent successfully");
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyOTP(@RequestBody OTPVerification request) {

        boolean result = otpService.validateOTP(request);

        return result
                ? ResponseEntity.ok("OTP verified successfully")
                : ResponseEntity.badRequest().body("Invalid OTP");
    }
}