package com.example.demo;

import java.math.BigDecimal;
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

		try {

			logger.info("========== CREATE ORDER START ==========");

			logger.info("STEP-1 Calling Product Service. ProductId={}", dto.getProductId());

			Product product = productClient.getProductById(dto.getProductId());

			logger.info("STEP-2 Product Response={}", product);

			if (product == null) {
				throw new ProductNotFoundException("Product not found");
			}

			logger.info("STEP-3 Product Price={}", product.getPrice());

			Order order = new Order();

			order.setProductId(dto.getProductId());
			order.setCustomerName(dto.getCustomerName());
			order.setCustomerAge(dto.getCustomerAge());
			order.setPreviousOrders(dto.getPreviousOrders());
			order.setQuantity(dto.getQuantity());
			order.setMobileNumber(dto.getMobileNumber());
			order.setDescription(dto.getDescription());
			order.setItems(dto.getItems());

			order.setAmount(product.getPrice().multiply(BigDecimal.valueOf(dto.getQuantity())));

			logger.info("STEP-4 Amount={}", order.getAmount());

			FraudResult result = checkFraudRisk(dto);

			logger.info("STEP-5 Fraud Result={}", result);

			FraudResponse aiResponse = aiClient.checkFraud(new FraudCheckRequest(order.getCustomerAge(),
					order.getPreviousOrders(), order.getAmount(), result.getFraudRiskLevel(), result.getRiskScore()));

			logger.info("STEP-6 AI Response={}", aiResponse);

			order.setFraudReason(aiResponse.getReason());
			order.setRecommendation(aiResponse.getRecommendation());

			logger.info("STEP-7 Saving Order");

			Order savedOrder = orderRepository.save(order);

			logger.info("STEP-8 Order Saved={}", savedOrder.getId());

			String longUrl = "http://api-gateway:8080/api/orders/" + savedOrder.getId();

			logger.info("STEP-9 Calling URL Shortener");

			String shortUrl = urlShortenerClient.shortenURL(new UrlRequest(longUrl));

			logger.info("STEP-10 Short URL={}", shortUrl);

			savedOrder.setShortURL(shortUrl);

			savedOrder = orderRepository.save(savedOrder);

			logger.info("STEP-11 Publishing Kafka Event");

			publishOrderEvent(savedOrder);

			logger.info("========== CREATE ORDER SUCCESS ==========");

			return savedOrder;

		} catch (Exception e) {

			logger.error("ORDER CREATION FAILED", e);

			throw e;
		}
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