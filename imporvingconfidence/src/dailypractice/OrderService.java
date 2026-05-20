package dailypractice;

import java.io.IOException;

public interface OrderService {

	Order createOrder(Order order) throws InterruptedException, IOException;
}
