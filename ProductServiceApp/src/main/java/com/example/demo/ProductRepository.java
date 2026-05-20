package com.example.demo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Long>{

	 Optional<Product> findByProductName(String productName);
	
	Optional<Product> findById(Long id);
	
	public void deleteById(Long id);
	
	public boolean existsById(Long id);
	
	public boolean existsByProductName(String productName);

	@Modifying
	@Transactional
	@Query("UPDATE Product p SET p.views = p.views + :views WHERE p.id = :productId")
	void incrementViews(Long productId, Long views);	
}
