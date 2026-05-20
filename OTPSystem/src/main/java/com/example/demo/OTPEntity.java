package com.example.demo;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="otptable")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OTPEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(unique = true)
	@NotNull
	@Email
	private String email;
	
	@Column(unique = true)
	@NotNull
	@Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
	@Size(min = 10, max = 10, message = "Phone number must be exactly 10 digits")
	private String phoneNumber;
	
	@NotBlank
	@Size(min = 6, max = 6, message = "OTP must be exactly 6 digits")
	@Pattern(regexp = "^[0-9]{6}$", message = "OTP must be 6 digits")
	
	private String otp;
	
	private int attemptCount;
	
	private LocalDateTime createdAt;
	private LocalDateTime expiresAt;
	
	private boolean isUsed;
	
	private LocalDateTime usedAt;
	
	private LocalDateTime updatedAt;
	
	private LocalDateTime deletedAt;
	
	
	
}
