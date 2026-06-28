package com.example.demo;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FraudResponse {

    private int riskScore;
    private String riskLevel;
    private String reason;
    private BigDecimal orderAmount;

}