package dailypractice;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class OrderServiceImpl implements OrderService {

    private final PaymentService paymentService;
    private final ExecutorService executor;

    public OrderServiceImpl(PaymentService paymentService, ExecutorService executor) {
        this.paymentService = paymentService;
        this.executor = executor;
    }

    @Override
    public Order createOrder(Order order) throws IOException {

        // 1. Validate input
        if (order == null ||
            order.getCustomerName() == null ||
            order.getAmount() == null) {
            throw new IllegalArgumentException("Invalid order details");
        }

        // 2. Generate Order ID (MANDATORY before async work)
        order.setOrderId(UUID.randomUUID().toString());

        // 3. Trigger async payment (fire-and-forget)
        CompletableFuture
            .supplyAsync(() -> {
                Payment payment = new Payment();
                payment.setOrderId(order.getOrderId());
                payment.setCustomerName(order.getCustomerName());
                payment.setAmount(order.getAmount());
                payment.setMobileNumber(order.getMobileNumber());
                payment.setPaymentMethod(order.getPaymentMethod());

                try {
                    return paymentService.processPayment(payment);
                } catch (InvalidPaymentException e) {
                    throw new RuntimeException(e); // propagate to CF
                }
            }, executor)
            .thenAccept(savedPayment -> {
                if (savedPayment != null) {
                    order.setPaymentMethod(savedPayment.getPaymentMethod());
                }
            })

            .orTimeout(3, TimeUnit.SECONDS)
            .exceptionally(ex -> {
                System.out.println(
                    "Payment failed for orderId " +
                    order.getOrderId() + " : " + ex.getMessage()
                );
                return null; // fallback / retry / event
            });

        // 4. Return order immediately (non-blocking)
        System.out.println("Order created successfully: " + order);
        return order;
    }
}
