package com.example.demo;

import java.math.BigDecimal;

import java.util.List;

import lombok.Data;

@Data
public class Order {
	private Long id;
	
	private Long productId;
	private String customerName;
	
	private String aiAnalysis;
	private String description;
	private BigDecimal amount;
	
	private String mobileNumber;
	

	private List<OrderItem> items;

	private int quantity;

private String shortURL;

}
