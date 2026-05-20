package com.example.demo;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/api/orders")
public class OrderController {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private OrderService orderService;

	@PostMapping()
	public ResponseEntity<Order> createOrder(@Validated @RequestBody OrderRequestDTO request)
			throws InValidOrderException {

		Order savedOrder = orderService.createOrder(request);
		logger.info("Order created with ID: {}", savedOrder.getId());
		return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
	}

	@GetMapping("/all")
	public Page<Order> getOrders(@RequestParam int page, @RequestParam int size) {
		logger.info("Fetching orders - Page: {}, Size: {}", page, size);
		return orderService.findAll(page, size);
	}
}
