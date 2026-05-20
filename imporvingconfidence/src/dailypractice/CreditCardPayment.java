package dailypractice;

import java.io.IOException;

public class CreditCardPayment implements PaymentService {

	private final PaymentService paymentService;
	
	
	public CreditCardPayment(PaymentService paymentService) {
		this.paymentService = paymentService;
	}


	@Override
	public Payment processPayment(Payment payment) throws InvalidPaymentException {
        System.out.println("Processing Credit Card payment");
        payment.setPaymentMethod(PaymentMethod.CREDIT_CARD);

		return payment;
	}

}
