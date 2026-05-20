package dailypractice;

import java.io.IOException;

public class UPIPayment implements PaymentService {

	private final PaymentService paymentService;
	
	
	public UPIPayment(PaymentService paymentService) {
		this.paymentService = paymentService;
	}



	    @Override
	    public Payment processPayment(Payment payment) {
	        System.out.println("Processing UPI payment");
	        payment.setPaymentMethod(PaymentMethod.UPI);
	        return payment;
	    }
	}
