package com.example.demo;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class OrderResponseDTO {

	    private Long id;
	    private Long productId;
	    private String customerName;
	    private String description;
	    private BigDecimal amount;
	    private String mobileNumber;
	    private List<OrderItem> items;
	    private String productName;
	    private BigDecimal price;
	    private Integer quantity;
		private String fraudRiskLevel;
		private int customerAge;
		private int previousOrders;

	    private String shortURL;

}
