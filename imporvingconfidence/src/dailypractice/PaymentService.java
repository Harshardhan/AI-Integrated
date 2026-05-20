package dailypractice;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public interface PaymentService {

	Payment processPayment(Payment payment) throws  InvalidPaymentException;
}
