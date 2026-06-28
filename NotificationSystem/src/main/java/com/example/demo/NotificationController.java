package com.example.demo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

	private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);
	private final NotificationService notificationService;

	public NotificationController(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	@PostMapping()
	public ResponseEntity<Notification> createNotification(@RequestBody @Valid Notification notification) {
		logger.info("Received request to create notification for email ID: {}", notification.getEmailId());
		Notification createdNotification = notificationService.createNotification(notification);
		logger.info("Notification created successfully with ID: {}", createdNotification.getId());
		
		return ResponseEntity.ok(createdNotification);
	}

	@PutMapping("/{id}")

	public ResponseEntity<Notification> updateNotification(@PathVariable Long id,
			@RequestBody @Valid Notification notification) {
		logger.info("Received request to update notification with ID: {}", id);

		Notification updatedNotification = notificationService.updateNotification(id, notification)
				.orElseThrow(() -> new NotificationNotFoundException("Notification not found with ID: " + id));
		logger.info("Notification updated successfully with ID: {}", id);
		return ResponseEntity.ok(updatedNotification);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Notification> getNotificationById(@PathVariable Long id) {

		logger.info("Received request to get notification with ID: {}", id);
		Notification notification = notificationService.getNotificationById(id);
		logger.info("Notification retrieved successfully with ID: {}", id);
		return ResponseEntity.ok(notification);
	}

	@GetMapping("/email/{emailId}")
	public ResponseEntity<List<Notification>> getNotificationByEmailId(@PathVariable String emailId) {
		logger.info("Received request to get notifications for email ID: {}", emailId);
		List<Notification> notifications = notificationService.getNotificationByEmailId(emailId);
		logger.info("Notifications retrieved successfully for email ID: {}", emailId);
		return ResponseEntity.ok(notifications);
	}

	@GetMapping("/all")
	public ResponseEntity<Iterable<Notification>> getAllNotifications() {
		logger.info("Received request to get all notifications");
		Iterable<Notification> notifications = notificationService.getAllNotifications();
		logger.info("All notifications retrieved successfully");
		return ResponseEntity.ok(notifications);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
		logger.info("Received request to delete notification with ID: {}", id);
		notificationService.deleteNotification(id);
		logger.info("Notification deleted successfully with ID: {}", id);
		return ResponseEntity.noContent().build();
	}

}