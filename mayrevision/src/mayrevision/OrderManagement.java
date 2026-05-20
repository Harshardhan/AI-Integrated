package mayrevision;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OrderManagement implements OrderService {

    private static final ExecutorService executorService =
            Executors.newFixedThreadPool(3);

    @Override
    public Order processOrder(Order order) {

        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }

        System.out.println("Processing order for: "
                + order.getCustomerName()
                + " by "
                + Thread.currentThread().getName());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Order processed for: "
                + order.getCustomerName()
                + " by "
                + Thread.currentThread().getName());

        return order;
    }

    public CompletableFuture<Order> processOrderAsync(Order order) {

        return CompletableFuture.supplyAsync(() -> processOrder(order), executorService);
    }

    public static void main(String[] args) {

        List<Order> orderList = new ArrayList<>();

        orderList.add(
                new Order(1L, "John Doe", "john@gmail.com",
                        "9232562536", "iphone14 1pc",
                        new BigDecimal("100000.00")));

        orderList.add(
                new Order(2L, "Jane Smith", "jane@gmail.com",
                        "9232562537", "samsung galaxy s23 1pc",
                        new BigDecimal("90000.00")));

        orderList.add(
                new Order(3L, "Alice Johnson", "alice@gmail.com",
                        "9232562538", "oneplus 11 1pc",
                        new BigDecimal("70000.00")));

        orderList.add(
                new Order(4L, "Bob Brown", "bob@gmail.com",
                        "9232562539", "google pixel 7 1pc",
                        new BigDecimal("80000.00")));

        orderList.add(
                new Order(5L, "Charlie Davis", "charlie@gmail.com",
                        "9232562540", "xiaomi 13 pro 1pc",
                        new BigDecimal("75000.00")));

        OrderManagement orderManagement = new OrderManagement();

        List<CompletableFuture<Order>> futures = new ArrayList<>();

        for (Order order : orderList) {

            CompletableFuture<Order> future =
                    orderManagement.processOrderAsync(order)
                            .thenApply(processedOrder -> {

                                System.out.println(
                                        "Sending notification for: "
                                                + processedOrder.getCustomerName());

                                return processedOrder;
                            })
                            .exceptionally(ex -> {

                                System.out.println("Error: " + ex.getMessage());
                                return null;
                            });

            futures.add(future);
        }

        // Wait for all tasks to complete
        CompletableFuture.allOf(
                futures.toArray(new CompletableFuture[0])
        ).join();

        executorService.shutdown();

        System.out.println("All orders processed successfully");
    }
}