package com.example.demo;

public interface OrderEventPublisher {

	void publishOrderCreatedEvent(Order order);
}
