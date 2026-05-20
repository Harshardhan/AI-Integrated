package com.example.demo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	private final ProductService productService;

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@PostMapping("/create")
	public ResponseEntity<Product> createProduct(@RequestBody @Valid Product product) {
		logger.info("Received request to create product: {}", product);
		Product createdProduct = productService.createProduct(product);
		logger.info("Product created successfully with ID: {}", createdProduct.getId());
		return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
	}

	@PostMapping("/{id}/view")
	public void addView(@PathVariable Long id) {
		logger.info("view and count the products with ID:{}", id);
	    productService.incrementView(id);
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody @Valid Product product) {
		logger.info("Received request to update product with ID: {}", id);

		Product updatedProduct = productService.updateProduct(id, product);
		logger.info("Product updated successfully with ID: {}", id);
		return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
	}

	@GetMapping("/{id}")

	public ResponseEntity<Product> getProductById(@PathVariable Long id) {
		logger.info("Received request to get product with ID: {}", id);
		Product product = productService.getProductById(id);
		logger.info("Product retrieved successfully with ID: {}", id);
		return new ResponseEntity<>(product, HttpStatus.OK);

	}

	@GetMapping("/all")
	public ResponseEntity<List<Product>> getAllProducts() {
		logger.info("Received request to get all products");
		List<Product> products = productService.findAllProducts();
		logger.info("Products retrieved successfully, count: {}", products.size());
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@GetMapping("/name/{productName}")
	public ResponseEntity<Product> getProductByName(@PathVariable String productName) {
		logger.info("Received request to get product with name: {}", productName);
		Product product = productService.getProductByName(productName);
		logger.info("Product retrieved successfully with name: {}", productName);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
		logger.info("Received request to delete product with ID: {}", id);
		productService.deleteProduct(id);
		logger.info("Product deleted successfully with ID: {}", id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
