package com.example.demo;

import java.util.List;
import java.util.Optional;

public interface PaymentService {
	
	
	Payment updatePayment(Long id, Payment payment);

	void deletePayment(Long id);
	
	Payment getPaymentById(Long id);
	
	List<Payment> getPaymentByUserId(Long userId);
	
	List<Payment> getPaymentByEmail(String email);
	
	List<Payment> getPaymentByPhoneNumber(String phoneNumber);
	
	List<Payment> getPaymentByRecipient(String recipient);
	
	boolean existsByUserId(Long userId);
	
	boolean existsByEmail(String email);
	
	List<Payment> findPaymentsByStatus(PaymentStatus paymentStatus);
	
	List<Payment> findAllPayments();

	Payment processPayment(Payment payment, String idempotencyKey);
	
}
