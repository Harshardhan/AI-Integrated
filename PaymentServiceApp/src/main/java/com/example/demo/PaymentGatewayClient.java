package com.example.demo;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Component
@Retryable(
	    value = RuntimeException.class,
	    maxAttempts = 3,
	    backoff = @Backoff(delay = 2000)
	)
public class PaymentGatewayClient {
	
	public boolean processPayment(Payment request) {
		// Simulate payment processing logic
		// In a real implementation, this would involve API calls to a payment gateway
		if (Math.random() > 0.2) {
			// Simulate successful payment processing
			return true;
		} else {
			// Simulate failed payment processing
			throw new RuntimeException("Payment processing failed");
		}
	}
}
