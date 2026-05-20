package com.example.demo;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	
	private final StringRedisTemplate redisTemplate;

	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	public ProductServiceImpl(ProductRepository productRepository, StringRedisTemplate redisTemplate) {
		this.productRepository = productRepository;
		this.redisTemplate = redisTemplate;
	}

	@Override
	@CachePut(value = "productById", key = "#result.id")
	@CacheEvict(value = "productList", allEntries = true)
	public Product createProduct(Product product) {

		if (product == null || product.getProductName() == null || product.getProductName().isEmpty()) {
			logger.error("Invalid product data: {}", product);
			throw new InValidProductException("Product name cannot be null or empty");
		}
		if (productRepository.existsByProductName(product.getProductName())) {
			logger.error("Product with name '{}' already exists", product.getProductName());
			throw new ProductAlreadyExistsException(
					"Product with name '" + product.getProductName() + "' already exists");
		}

		Product savedProduct = productRepository.save(product);
		logger.info("Product created successfully with ID: {}", savedProduct.getId());
		return savedProduct;
	}

	@Override
	@Cacheable(value = "productById", key = "#id", sync = true)
	public Product getProductById(Long id) {

		logger.info("Fetching product with ID: {}", id);
	    logger.info("🔥 DATA COMING FROM DATABASE for id: {}" , id);

		return productRepository.findById(id).map(product -> {
			logger.info("Product found with ID: {}", id);
			return product;
		}).orElseThrow(() -> {
			logger.error("Product not found with ID: {}", id);
			return new ProductNotFoundException("Product not found with ID: " + id);
		});
	}

	@Override
	@CacheEvict(value = "productList", allEntries = true) // Clear product list cache
	@CachePut(value = "productById", key = "#id") // Update cache for individual product
	public Product updateProduct(Long id, Product product) {

		Product existingProduct = productRepository.findById(id).orElseThrow(() -> {
			logger.error("Product not found with ID: {}", id);
			return new ProductNotFoundException("Product not found with ID: " + id);
		});

		existingProduct.setProductName(product.getProductName());
		existingProduct.setDescription(product.getDescription());
		existingProduct.setPrice(product.getPrice());
		existingProduct.setCategory(product.getCategory());
		existingProduct.setStockQuantity(product.getStockQuantity());
		existingProduct.setImageUrl(product.getImageUrl());

		Product updatedProduct = productRepository.save(existingProduct);
		logger.info("Product updated successfully with ID: {}", id);

		return updatedProduct;
	}

	@Override
	@CacheEvict(value = "productById", key = "#id")
	public void deleteProduct(Long id) {
		if (!productRepository.existsById(id)) {
			logger.error("Product not found with ID: {}", id);
			throw new ProductNotFoundException("Product not found with ID: " + id);
		}
		productRepository.deleteById(id);
		logger.info("Product deleted successfully with ID: {}", id);

	}

	@Override
	public Product getProductByName(String productName) {
		return productRepository.findByProductName(productName).orElseThrow(() -> {
			logger.error("Product not found with name: {}", productName);
			return new ProductNotFoundException("Product not found with name: " + productName);
		});
	}

	@Override
	@Cacheable(value = "productList")

	public List<Product> findAllProducts() {

		List<Product> products = productRepository.findAll();
		logger.info("Fetched all products, count: {}", products.size());
		logger.info("🔥 DATA COMING FROM DATABASE for all products");
		return products;
	}

	@Override
	public void incrementView(Long productId) {
	    String key = "product:" + productId + ":views";
	    redisTemplate.opsForValue().increment(key);
	}
	
	

	@Scheduled(fixedRate = 60000)
	public void syncViews() {

	    Set<String> keys = redisTemplate.keys("product:*:views");

	    if (keys == null || keys.isEmpty()) return;

	    for (String key : keys) {

	        String value = redisTemplate.opsForValue().get(key);
	        Long views = (value != null) ? Long.parseLong(value) : 0;

	        // Extract productId
	        Long productId = Long.parseLong(key.split(":")[1]);

	        // Update DB
	        productRepository.incrementViews(productId, views);

	        // Reset Redis count
	        redisTemplate.delete(key);
	    }

	    System.out.println("✅ Views synced to DB");
	}
}
