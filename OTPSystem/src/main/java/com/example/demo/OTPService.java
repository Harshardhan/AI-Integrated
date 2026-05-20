package com.example.demo;

public interface OTPService {

    void generateOTP(OTPRequest request);

    boolean validateOTP(OTPVerifyRequest request);
}