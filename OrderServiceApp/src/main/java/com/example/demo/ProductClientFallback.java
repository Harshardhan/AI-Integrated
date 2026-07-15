package com.example.demo;

public class ProductClientFallback implements ProductClient {

	@Override
	public Product getProductById(Long id) {
		// Return a default product or null when the product service is unavailable
		Product fallbackProduct = new Product();
		fallbackProduct.setId(id);
		fallbackProduct.setProductName("Default Product");
		fallbackProduct.setCategory("unknown");
		return fallbackProduct;
	
	}
}
