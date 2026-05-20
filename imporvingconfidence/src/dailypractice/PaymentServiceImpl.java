package dailypractice;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class PaymentServiceImpl implements PaymentService {

    // Idempotency store
    private static final Set<String> processedOrders =
            ConcurrentHashMap.newKeySet();

    @Override
    public Payment processPayment(Payment payment) throws InvalidPaymentException {

        if (payment == null) {
            throw new IllegalArgumentException("Invalid payment details.");
        }

        if (payment.getOrderId() == null || payment.getOrderId().isBlank()) {
            throw new InvalidPaymentException("orderId must not be null or empty");
        }

        // Idempotency check
        if (!processedOrders.add(payment.getOrderId())) {
            throw new InvalidPaymentException(
                "Duplicate payment attempt for orderId " + payment.getOrderId()
            );
        }

        // Handle payment based on runtime choice
        switch (payment.getPaymentMethod()) {
            case CASH -> {
                System.out.println("Processing CASH payment for order " + payment.getOrderId());
                // Simulate cash logic
            }
            case CREDIT_CARD -> {
                System.out.println("Processing CREDIT CARD payment for order " + payment.getOrderId());
                // Simulate card logic
            }
            case UPI -> {
                System.out.println("Processing UPI payment for order " + payment.getOrderId());
                // Simulate UPI logic
            }
            default -> throw new InvalidPaymentException("Unsupported payment method");
        }

        Payment savedPayment = new Payment();
        savedPayment.setOrderId(payment.getOrderId());
        savedPayment.setCustomerName(payment.getCustomerName());
        savedPayment.setAmount(payment.getAmount());
        savedPayment.setMobileNumber(payment.getMobileNumber());
        savedPayment.setPaymentMethod(payment.getPaymentMethod());

        System.out.println("Payment processed successfully for orderId " + payment.getOrderId());
        return savedPayment;
    }
}
