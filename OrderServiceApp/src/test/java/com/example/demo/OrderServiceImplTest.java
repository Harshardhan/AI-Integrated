package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductClient productClient;

    @Mock
    private OrderEventPublisher orderEventPublisher;

    @Mock
    private URLShortenerClient urlShortenerClient;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    void createOrder_success() {

        // Arrange
        OrderRequestDTO dto = new OrderRequestDTO();
        dto.setProductId(1L);
        dto.setQuantity(2);
        dto.setCustomerName("John");
        dto.setDescription("Test Order");
        dto.setMobileNumber("9999999999");
        dto.setItems(Collections.emptyList());

        Product product = new Product();
        product.setPrice(BigDecimal.valueOf(100));

        when(productClient.getProductById(1L)).thenReturn(product);

        Order savedOrder = new Order();
        savedOrder.setId(10L);

        when(orderRepository.save(any(Order.class)))
                .thenReturn(savedOrder);

        when(urlShortenerClient.shortenURL(any()))
                .thenReturn("short-url");

        // Act
        Order result = orderService.createOrder(dto);

        // Assert
        assertNotNull(result);
        assertEquals("short-url", result.getShortURL());

        verify(orderRepository, atLeastOnce()).save(any(Order.class));
        verify(orderEventPublisher).publishOrderCreatedEvent(any(Order.class));
    }

    @Test
    void createOrder_productNotFound_shouldThrowException() {

        // Arrange
        OrderRequestDTO dto = new OrderRequestDTO();
        dto.setProductId(1L);

        when(productClient.getProductById(1L)).thenReturn(null);

        // Act + Assert
        assertThrows(ProductNotFoundException.class, () -> {
            orderService.createOrder(dto);
        });

        verify(orderRepository, never()).save(any());
    }
}