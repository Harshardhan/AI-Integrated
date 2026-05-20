package com.example.demo;

import java.util.List;
import java.util.Optional;

public interface NotificationService {

	Notification createNotification(Notification notification);

	Notification getNotificationById(Long id);

	List<Notification> getNotificationByEmailId(String emailId);

	Iterable<Notification> getAllNotifications();

	void deleteNotification(Long id);

	Optional<Notification> updateNotification(Long id, Notification notification);

	void sendOtpEmail(UserEvent event);

	void sendWelcomeEmail(UserEvent event);

}
