package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

	private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);
	private final PaymentRepository paymentRepository;

	private final PaymentGatewayClient paymentGatewayClient;

	private final IdempotencyRepository idempotencyRepository;

	public PaymentServiceImpl(PaymentRepository paymentRepository, PaymentGatewayClient paymentGatewayClient,
			IdempotencyRepository idempotencyRepository) {
		this.paymentRepository = paymentRepository;
		this.paymentGatewayClient = paymentGatewayClient;
		this.idempotencyRepository = idempotencyRepository;
	}

	@Override
	public Payment processPayment(Payment payment, String idempotencyKey) {
		
		Optional<Payment> existingPayment = idempotencyRepository.findByIdempotencyKey(idempotencyKey);

		if(existingPayment.isPresent()) {
			logger.info("Idempotent request detected. Returning existing payment with ID: {}", existingPayment.get().getId());
			return existingPayment.get();
		}
	    try {
	        // Step 1: Create payment with unique idempotency key
	        Payment newPayment = new Payment();
	        newPayment.setUserId(payment.getUserId());
	        newPayment.setEmail(payment.getEmail());
	        newPayment.setAmount(payment.getAmount());
	        newPayment.setPaymentStatus(PaymentStatus.PROCESSING);
	        newPayment.setIdempotencyKey(idempotencyKey);

	        newPayment = paymentRepository.save(newPayment);

	        logger.info("Payment created with ID: {}", newPayment.getId());

	        // Step 2: Call Gateway
	        boolean success = paymentGatewayClient.processPayment(newPayment);

	        if (success) {
	            newPayment.setPaymentStatus(PaymentStatus.COMPLETED);
	        } else {
	            newPayment.setPaymentStatus(PaymentStatus.FAILED);
	        }

	        return paymentRepository.save(newPayment);

	    } catch (DataIntegrityViolationException ex) {

	        // 🔥 Handles duplicate request safely
	        logger.warn("Duplicate idempotencyKey detected: {}", idempotencyKey);

	        return paymentRepository.findByIdempotencyKey(idempotencyKey)
	                .orElseThrow(() -> new RuntimeException("Payment not found after duplicate key"));
	    } catch (Exception ex) {

	        logger.error("Payment processing failed: {}", ex.getMessage());

	        throw new RuntimeException("Payment failed");
	    }
	}
	@Override
	public Payment updatePayment(Long id, Payment payment) {
		Payment existingPayment = paymentRepository.findById(id)
				.orElseThrow(() -> new PaymentNotFoundException("Payment not found with id: " + id));

		existingPayment.setEmail(payment.getEmail());
		existingPayment.setAmount(payment.getAmount());
		existingPayment.setPhoneNumber(payment.getPhoneNumber());
		existingPayment.setPaymentMethod(payment.getPaymentMethod());
		existingPayment.setPaymentStatus(payment.getPaymentStatus());
		existingPayment.setRecipient(payment.getRecipient());
		existingPayment.setSender(payment.getSender());
		existingPayment.setDescription(payment.getDescription());
		existingPayment.setUserId(payment.getUserId());

		Payment updatedPayment = paymentRepository.save(existingPayment);
		logger.info("Updated payment with id: {}", id);
		return updatedPayment;
	}

	@Override
	public void deletePayment(Long id) {

		if (!paymentRepository.existsById(id)) {
			logger.error("Payment not found with id: {}", id);
			throw new PaymentNotFoundException("Payment not found with id: " + id);
		}
		paymentRepository.deleteById(id);
		logger.info("Deleted payment with id: {}", id);
	}

	@Override
	public Payment getPaymentById(Long id) {
		logger.info("Retrieving payment with id: {}", id);
		return paymentRepository.findById(id)
				.orElseThrow(() -> new PaymentNotFoundException("Payment not found with id: " + id));
	}

	@Override
	public List<Payment> getPaymentByUserId(Long userId) {
		logger.info("Retrieving payment for userId: {}", userId);
		List<Payment> payments = paymentRepository.findByUserId(userId);
		if (payments.isEmpty()) {
			logger.warn("No payments found for userId: {}", userId);
			throw new PaymentNotFoundException("No payments found for userId: " + userId);
		}
		return payments;
	}

	@Override
	public List<Payment> getPaymentByEmail(String email) {
		
		logger.info("Retrieving payment for email: {}", email);
		List<Payment> payments = paymentRepository.findByEmail(email);
		if (payments.isEmpty()) {
			logger.warn("No payments found for email: {}", email);
		}
		return payments;
	}

	@Override
	public List<Payment> getPaymentByPhoneNumber(String phoneNumber) {
		logger.info("Retrieving payment for phoneNumber: {}", phoneNumber);
		List<Payment> payment = paymentRepository.findByPhoneNumber(phoneNumber);
		if (payment.isEmpty()) {
			logger.warn("No payment found for phoneNumber: {}", phoneNumber);
			throw new PaymentNotFoundException("Payment not found for phoneNumber: " + phoneNumber);
		}
		return payment;

	}

	@Override
	public List<Payment> getPaymentByRecipient(String recipient) {
		logger.info("Retrieving payment for recipient: {}", recipient);
		List<Payment> payments = paymentRepository.findByRecipient(recipient);
		if (payments.isEmpty()) {
			logger.warn("No payments found for recipient: {}", recipient);
			throw new PaymentNotFoundException("No payments found for recipient: " + recipient);
		}
		return payments;
	}

	@Override
	public boolean existsByUserId(Long userId) {
		logger.info("Checking existence of payment for userId: {}", userId);
		return paymentRepository.existsByUserId(userId);
	}

	@Override
	public boolean existsByEmail(String email) {
		logger.info("Checking existence of payment for email: {}", email);
		return paymentRepository.existsByEmail(email);

	}

	@Override
	public List<Payment> findPaymentsByStatus(PaymentStatus paymentStatus) {

		logger.info("Finding payments with status: {}", paymentStatus);
		List<Payment> payments = paymentRepository.findByPaymentStatus(paymentStatus);
		if (payments.isEmpty()) {
			logger.warn("No payments found with status: {}", paymentStatus);
			throw new PaymentNotFoundException("No payments found with status: " + paymentStatus);
		}
		return payments;
	}

	@Override
	public List<Payment> findAllPayments() {
		logger.info("Retrieving all payments");
		List<Payment> payments = paymentRepository.findAll();
		if (payments.isEmpty()) {
			logger.warn("No payments found");
			throw new PaymentNotFoundException("No payments found");
		}
		return payments;
	}

}
