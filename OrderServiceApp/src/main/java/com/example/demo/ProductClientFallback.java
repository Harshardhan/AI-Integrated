package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class ProductClientFallback implements ProductClient {

    @Override
    public Product getProductById(Long id) {

        throw new RuntimeException(
            "Product Service unavailable.");
    }

}