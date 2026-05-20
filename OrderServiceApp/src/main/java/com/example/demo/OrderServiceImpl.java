package com.example.demo;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional

public class OrderServiceImpl implements OrderService {

	private final AIClient aiClient;
	private final OrderRepository orderRepository;

	private final OrderEventPublisher orderEventPublisher;
	private final ProductClient productClient;

	private final URLShortenerClient urlShortenerClient;

	private final PaymentClient paymentClient;

	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

	public OrderServiceImpl(AIClient aiClient, OrderRepository orderRepository, OrderEventPublisher orderEventPublisher,
			ProductClient productClient, URLShortenerClient urlShortenerClient, PaymentClient paymentClient) {
		this.aiClient = aiClient;
		this.orderRepository = orderRepository;
		this.orderEventPublisher = orderEventPublisher;
		this.productClient = productClient;
		this.urlShortenerClient = urlShortenerClient;
		this.paymentClient = paymentClient;
	}

	@Override
	public Order createOrder(OrderRequestDTO dto) {

		Product product = productClient.getProductById(dto.getProductId());

		if (product == null) {
			logger.error("Product not found with ID: {}", dto.getProductId());
			throw new ProductNotFoundException("Product not found");
		}

		Order order = new Order();
		order.setProductId(dto.getProductId());
		order.setQuantity(dto.getQuantity());
		order.setCustomerName(dto.getCustomerName());

		// Calculate amount from product price
		order.setAmount(product.getPrice().multiply(java.math.BigDecimal.valueOf(dto.getQuantity())));

		order.setDescription(dto.getDescription());
		order.setMobileNumber(dto.getMobileNumber());

		if (dto.getItems() != null) {
			for (OrderItem item : dto.getItems()) {
				item.setOrder(order); // ✅ VERY IMPORTANT
			}
		}

		order.setItems(dto.getItems());
		// 🔥 FIX ENDS HERE

		logger.info("Order created successfully");
		logger.info("Product details: {}", product);

		// Save order to database

		Order savedOrder = orderRepository.save(order);
		// 🔥 AI CALL START
		long start = System.nanoTime();

		String aiPrompt = """
				Analyze this order and detect fraud risk.

				Product ID: %d
				Quantity: %d
				Amount: %s
				Customer: %s

				Return:
				- Risk Level (LOW / MEDIUM / HIGH)
				- Reason
				""".formatted(
				        savedOrder.getProductId(),
				        savedOrder.getQuantity(),
				        savedOrder.getAmount(),
				        savedOrder.getCustomerName()
				);

				String aiResponse = aiClient.ask(aiPrompt);
		long duration = System.nanoTime() - start;

		logger.info("AI Response Time: {} ms", duration / 1_000_000);
		logger.info("AI Response: {}", aiResponse);
		
		CompletableFuture<String> payment =
			    CompletableFuture.supplyAsync(() -> {
			        try {
			            Payment paymentRequest = new Payment();

			            paymentRequest.setUserId(savedOrder.getId());
			            paymentRequest.setAmount(savedOrder.getAmount());

			            return "Payment Success";

			        } catch (Exception e) {
			            logger.error("Payment failed", e);
			            return "Payment Failed";
			        }
			    });		


		// (optional) store AI result
		savedOrder.setAiAnalysis(aiResponse);

		// 🔥 AI CALL END

		// Short URL
		String longUrl = "http://api-gateway:8080/api/orders/" + savedOrder.getId();
		String shortUrl = urlShortenerClient.shortenURL(new UrlRequest(longUrl));

		savedOrder.setShortURL(shortUrl);

		orderRepository.save(savedOrder);

		publishOrderEvent(savedOrder);

		return savedOrder;

	}

	private void publishOrderEvent(Order order) {
		try {
			orderEventPublisher.publishOrderCreatedEvent(order);
			logger.info("OrderCreated event published for order ID: {}", order.getId());
		} catch (Exception e) {
			logger.error("Failed to publish OrderCreated event for order ID: {}", order.getId(), e);
		}
	}

	@Override
	public Page<Order> findAll(int page, int size) {
		return orderRepository.findAll(PageRequest.of(page, size));
	}

}