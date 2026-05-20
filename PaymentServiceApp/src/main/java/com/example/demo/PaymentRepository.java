package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
	
	List<Payment> findByUserId(Long userId);
	
	List<Payment> findByEmail(String email);

	List<Payment> findByPaymentStatus(PaymentStatus paymentStatus);
	
	List<Payment> findByPhoneNumber(String phoneNumber);
	
	List<Payment> findByRecipient(String recipient);
	
	boolean existsByUserId(Long userId);
	
	boolean existsByEmail(String email);

	Optional<Payment> findByIdempotencyKey(String idempotencyKey);
	
	
}
