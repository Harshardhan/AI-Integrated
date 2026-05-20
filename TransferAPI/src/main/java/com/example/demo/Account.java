package com.example.demo;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Negative;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "account")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @NotBlank(message = "Name cannot be empty")
	private String accountHolder;

	private double balance;
	@Positive(message = "Amount must be positive")
	private BigDecimal amount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Account(Long id, String accountHolder, double balance, BigDecimal amount) {
		super();
		this.id = id;
		this.accountHolder = accountHolder;
		this.balance = balance;
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", accountHolder=" + accountHolder + ", balance=" + balance + ", amount=" + amount
				+ "]";
	}

	public Account() {

	}
}
