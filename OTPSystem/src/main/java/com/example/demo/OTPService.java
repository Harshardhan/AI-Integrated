package com.example.demo;

public interface OTPService {

    void generateOTP(OTPRequest request);

    boolean validateOTP(OTPVerification request);
}