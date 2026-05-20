package com.example.demo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
	private Long id;

	private Long userId;
	private String email;
	private String phoneNumber;
	private PaymentMethod paymentMethod;

	private PaymentStatus paymentStatus;
	private String recipient;
	private String sender;
	private String description;

	private String idempotencyKey;

	private BigDecimal amount;

	private LocalDateTime createdAt = LocalDateTime.now();

	private LocalDateTime updatedAt;

}
