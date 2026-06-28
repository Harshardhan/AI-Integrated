package com.example.demo;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;



@Component
public class OtpVerifiedConsumer {

    private final UserService userService;

    public OtpVerifiedConsumer(UserService userService) {
        this.userService = userService;
    }

    @KafkaListener(
        topics = "otp-verified-topic",
        groupId = "user-group"
    )
    public void consume(OtpVerifiedEvent event) {

        userService.activateUser(event.getEmailId());
    }
}