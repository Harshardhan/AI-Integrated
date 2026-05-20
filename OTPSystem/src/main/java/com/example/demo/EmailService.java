package com.example.demo;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendOTP(String email, String otp) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("your-email@gmail.com");
        message.setTo(email);
        message.setSubject("OTP Verification");
        message.setText("Your OTP is: " + otp);

        mailSender.send(message);
    }

	public void sendWelcomeEmail(String email) {
				SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("your-email@gmail.com");
		message.setTo(email);
		message.setSubject("Welcome to BranchPickingSystem");
		message.setText("Your registration is successful. Welcome to Branch Picking System!");
		mailSender.send(message);
		
	}

		
    
    
}