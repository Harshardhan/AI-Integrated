package com.example.demo;

import java.math.BigDecimal;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class OrderRequestDTO {

	private Long id;
	private Long productId;
    @NotBlank(message = "Customer name is required")
    private String customerName;
	private String fraudRiskLevel;
	private int customerAge;
	private int previousOrders;


    @Positive(message = "Amount must be greater than zero")
    private BigDecimal amount;

    @NotBlank(message = "Mobile number is required")
    @Pattern(
        regexp = "^[6-9][0-9]{9}$",
        message = "Invalid mobile number"
    )
    private String mobileNumber;
    
    private String shortURL;
    @NotBlank(message = "Description is required")
    private String description;
    
    private List<OrderItem> items;
    
    private int quantity;

}


