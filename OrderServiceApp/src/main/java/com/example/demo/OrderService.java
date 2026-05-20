package com.example.demo;


import org.springframework.data.domain.Page;

public interface OrderService {

	Order createOrder(OrderRequestDTO dto);

	Page<Order> findAll(int page, int size);

	
}
