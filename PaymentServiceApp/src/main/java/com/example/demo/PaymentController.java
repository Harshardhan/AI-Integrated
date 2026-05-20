package com.example.demo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

	private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

	private final PaymentService paymentService;

	public PaymentController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	@PostMapping()
	public ResponseEntity<Payment> processPayment(@RequestBody @Valid Payment payment, @RequestHeader("Idempotency-Key") String idempotencyKey){
		logger.info("Received payment request for userId: {}, amount: {}", payment.getUserId(), payment.getAmount());
		Payment result = paymentService.processPayment(payment, idempotencyKey);
		return ResponseEntity.ok(result);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Payment> updatePayment(@PathVariable Long id, @RequestBody @Valid Payment payment) {
		logger.info("Received update payment request for id: {}, userId: {}, amount: {}", id, payment.getUserId(),
				payment.getAmount());
		Payment result = paymentService.updatePayment(id, payment);
		return ResponseEntity.ok(result);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
		logger.info("Received delete payment request for id: {}", id);
		paymentService.deletePayment(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
		logger.info("Received get payment request for id: {}", id);
		Payment result = paymentService.getPaymentById(id);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Payment>> getPaymentByUserId(@PathVariable Long userId) {
		logger.info("Received get payment request for userId: {}", userId);
		List<Payment> result = paymentService.getPaymentByUserId(userId);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<List<Payment>> getPaymentByEmail(@PathVariable String email) {
		logger.info("Received get payment request for email: {}", email);
		List<Payment> result = paymentService.getPaymentByEmail(email);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/phone/{phoneNumber}")
	public ResponseEntity<List<Payment>> getPaymentByPhoneNumber(@PathVariable String phoneNumber) {
		logger.info("Received get payment request for phoneNumber: {}", phoneNumber);
		List<Payment> result = paymentService.getPaymentByPhoneNumber(phoneNumber);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/recipient/{recipient}")
	public ResponseEntity<List<Payment>> getPaymentByRecipient(@PathVariable String recipient) {
		logger.info("Received get payment request for recipient: {}", recipient);
		List<Payment> result = paymentService.getPaymentByRecipient(recipient);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/exists/user/{userId}")
	public ResponseEntity<Boolean> existsByUserId(@PathVariable Long userId) {
		logger.info("Received check payment existence request for userId: {}", userId);
		boolean exists = paymentService.existsByUserId(userId);
		return ResponseEntity.ok(exists);
	}
	
	@GetMapping("/exists/email/{email}")
		public ResponseEntity<Boolean> existsByEmail(@PathVariable String email) {
		logger.info("Received check payment existence request for email: {}", email);
		boolean exists = paymentService.existsByEmail(email);
		return ResponseEntity.ok(exists);
		}
	
	
	@GetMapping("/status/{paymentStatus}")
	public ResponseEntity<?> findPaymentsByStatus(@PathVariable PaymentStatus paymentStatus) {
		logger.info("Received get payments request for status: {}", paymentStatus);
		return ResponseEntity.ok(paymentService.findPaymentsByStatus(paymentStatus));
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Payment>> findAllPayments() {
		logger.info("Received get all payments request");
		List<Payment> payments = paymentService.findAllPayments();
		if (payments.isEmpty()) {
			
			logger.warn("No payments found");
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(payments);
		}
	}
}
