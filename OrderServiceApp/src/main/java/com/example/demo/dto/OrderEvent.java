package com.example.demo.dto;

import java.math.BigDecimal;
import java.util.List;

import com.example.demo.OrderItem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderEvent {
	
	private Long orderId;
	
	private String productName;
	
	private String customerName;
	
	private BigDecimal amount;
	
	private String mobileNumber;
	
	private List<OrderItem> items;
	private String description;
	
	private int quantity;
	

}
