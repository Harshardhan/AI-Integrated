package com.example.demo;
public interface UserEventPublisher {

	void publishUserCreatedEvent(User user);

	void publishUserDeletedEvent(User user);

	void publishUserVerifiedEvent(User user); // ✅ ADD THIS
}