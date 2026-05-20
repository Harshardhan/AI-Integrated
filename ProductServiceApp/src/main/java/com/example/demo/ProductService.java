package com.example.demo;

import java.util.List;

public interface ProductService {

	public Product createProduct(Product product);
	
	public Product getProductById(Long id);
	
	public Product updateProduct(Long id, Product product);
	
	public void deleteProduct(Long id);
	
	public Product getProductByName(String productName);
	
	public List<Product> findAllProducts();

	void incrementView(Long productId);
	
	
}
