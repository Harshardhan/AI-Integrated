package com.example.demo;

import java.math.BigDecimal;
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

		logger.info("Calling product service to fetch product details for productId: {}", dto.getProductId());
		Product product = productClient.getProductById(dto.getProductId());
		logger.info("product: ", product);

		if (product == null) {
			logger.error("Product not found with ID: {}", dto.getProductId());
			throw new ProductNotFoundException("Product not found");
		}
		logger.info("creating order");

		// 1. Create Order
		Order order = new Order();

		// 2. Set fields
		order.setProductId(dto.getProductId());
		order.setCustomerName(dto.getCustomerName());
		order.setCustomerAge(dto.getCustomerAge());
		order.setPreviousOrders(dto.getPreviousOrders());
		order.setQuantity(dto.getQuantity());
		order.setMobileNumber(dto.getMobileNumber());
		order.setDescription(dto.getDescription());
		order.setItems(dto.getItems());
		order.setAmount(product.getPrice().multiply(BigDecimal.valueOf(dto.getQuantity())));
		

		// 3. Calculate amount

		// 4. Calculate fraud score
		FraudResult result = checkFraudRisk(dto);

		order.setRiskScore(result.getRiskScore());
		order.setFraudRiskLevel(result.getFraudRiskLevel());

		// 5. AI explanation
		logger.info("Calling AI service to get explanation for fraud risk level: {}", result.getFraudRiskLevel());
		FraudResponse aiResponse = aiClient.checkFraud(
		    new FraudCheckRequest(
		        order.getCustomerAge(),
		        order.getPreviousOrders(),
		        order.getAmount(),
		        result.getFraudRiskLevel(),
		        result.getRiskScore()
		    )
		);

		order.setFraudReason(aiResponse.getReason());
		order.setRecommendation(aiResponse.getRecommendation());

		// 6. Save order
		logger.info("Saving order to the database");
		Order savedOrder = orderRepository.save(order);

		// 7. Generate short URL (needs generated ID)
		String longUrl = "http://api-gateway:8080/api/orders/" + savedOrder.getId();
		String shortUrl = urlShortenerClient.shortenURL(new UrlRequest(longUrl));

		savedOrder.setShortURL(shortUrl);

		// 8. Save again to update short URL
		savedOrder = orderRepository.save(savedOrder);

		// 9. Publish event
		publishOrderEvent(savedOrder);

		return savedOrder;
	}
	private FraudResult checkFraudRisk(OrderRequestDTO dto) {

		int score = 0;

		if (dto.getCustomerAge() < 18) {
			score += 20;
		} else if (dto.getCustomerAge() > 60) {
			score += 10;
		}

		if (dto.getAmount() != null && dto.getAmount().doubleValue() > 100000) {
			score += 30;
		}

		if (dto.getPreviousOrders() == 0) {
			score += 20;
		}

		String level;

		if (score >= 70)
			level = "High";
		else if (score >= 40)
			level = "Medium";
		else
			level = "Low";

		return new FraudResult(score, level);
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