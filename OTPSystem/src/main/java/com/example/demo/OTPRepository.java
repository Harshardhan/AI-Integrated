package com.example.demo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OTPRepository extends JpaRepository<OTPEntity, Long> {

	void deleteByExpiresAtBefore(java.time.LocalDateTime now);

	Optional<OTPEntity> findByEmailAndPhoneNumber(String email, String phoneNumber);
}
