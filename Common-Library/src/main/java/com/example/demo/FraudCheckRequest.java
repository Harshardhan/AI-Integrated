package com.example.demo;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FraudCheckRequest {

    private int customerAge;
    private int previousOrders;
    private BigDecimal orderAmount;
    private String fraudRiskLevel;
    private int riskScore;

}