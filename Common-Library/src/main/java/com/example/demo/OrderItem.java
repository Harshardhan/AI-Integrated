package com.example.demo;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OrderItem {

	private Long id;
	private String productName;
	
	private BigDecimal price;
	
	private Integer quantity;
		

    private Order order;

}
