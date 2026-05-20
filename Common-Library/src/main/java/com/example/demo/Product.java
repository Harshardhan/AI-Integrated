package com.example.demo;

import java.math.BigDecimal;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	private Long productId;
	
	private String productName;
	
	private String description;
	
	private BigDecimal price;
	
	
	private String category;
	
	private int stockQuantity;
	
	private String imageUrl;
	
}
