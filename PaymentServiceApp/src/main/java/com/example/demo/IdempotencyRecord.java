package com.example.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "idempotency")
@Getter
@Setter
public class IdempotencyRecord {

    @Id
    @Column(unique = true, nullable = false)
    private String idempotencyKey;

    private Long paymentId;

    private PaymentStatus paymentStatus;
}