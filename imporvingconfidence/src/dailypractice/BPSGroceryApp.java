package dailypractice;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BPSGroceryApp {

    public static void main(String[] args) throws Exception {

       /* ExecutorService executor = Executors.newFixedThreadPool(5);

        PaymentServiceImpl paymentService = new PaymentServiceImpl();
        OrderServiceImpl orderService = new OrderServiceImpl(paymentService, executor);

        // User chooses CASH, CREDIT_CARD, or UPI at runtime
        PaymentMethod chosenMethod = PaymentMethod.CREDIT_CARD;

        Order order = new Order(
                1L,
                "Harshavardhan",
                null,
                "9160819142",
                BigDecimal.valueOf(2000),
                chosenMethod // <-- runtime choice
        );

        Order createdOrder = orderService.createOrder(order);

        // Wait a little to let async payment finish before printing final order
        Thread.sleep(1000);

        System.out.println("Final Order Response: " + createdOrder);

        executor.shutdown(); */
    
    	Calculator a = new Calculator(new BigDecimal("10.50"), "INR");
    	Calculator b = new Calculator(new BigDecimal("5.25"), "INR");

    	CalculatorService service = new CalculatorServiceImpl();
    	System.out.println(service.add(a, b));

    }
    	
}
