package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository  extends JpaRepository<Notification, Long>{

	Optional<Notification> findById(Long id);
	
	List<Notification> findByEmailId(String emailId);	
	List<Notification> findByRecipient(String recipient);
	
	List<Notification> findBySender(String sender);
	
	Optional<Notification> findByMessage(String message);
	
	
}
