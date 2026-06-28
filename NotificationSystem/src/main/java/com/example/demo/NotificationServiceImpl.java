package com.example.demo;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

	private static final Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);
	private final NotificationRepository notificationRepository;

	private EmailService emailService;
	private final JavaMailSender mailSender;

	public NotificationServiceImpl(NotificationRepository notificationRepository, JavaMailSender mailSender) {
		this.notificationRepository = notificationRepository;
		this.mailSender = mailSender;
	}

	@Override
	public Notification createNotification(Notification notification) {

		if (notification == null) {
			logger.error("Notification object is null");
			throw new InValidNotificationException("Notification cannot be null");
		}

		if (notification.getEmailId() == null || notification.getEmailId().isEmpty()) {
			logger.error("Email ID is null or empty");
			throw new InValidNotificationException("Email ID cannot be null or empty");
		}

		Notification savedNotification = notificationRepository.save(notification);

		logger.info("Notification created with ID: {}", savedNotification.getId());
		
		emailService.sendWelcomeEmail(notification.getEmailId());
		logger.info("Welcome email sent to: {}", notification.getEmailId());
		return savedNotification;
	}

	@Override
	public Notification getNotificationById(Long id) {
		Notification notification = notificationRepository.findById(id)
				.orElseThrow(() -> new NotificationNotFoundException("Notification not found with ID: " + id));
		logger.info("Notification retrieved with ID: {}", id);
		return notification;
	}

	@Override
	public List<Notification> getNotificationByEmailId(String emailId) {

		List<Notification> notifications = notificationRepository.findByEmailId(emailId);
		if (notifications.isEmpty()) {
			logger.warn("No notifications found for email ID: {}", emailId);
		} else {
			logger.info("Retrieved {} notifications for email ID: {}", notifications.size(), emailId);
		}
		return notifications;

	}

	@Override
	public Iterable<Notification> getAllNotifications() {

		Iterable<Notification> notifications = notificationRepository.findAll();
		if (notifications.iterator().hasNext() == false) {
			logger.warn("No notifications found in the database");
		} else {
			logger.info("Retrieved {} notifications from the database", ((Collection<?>) notifications).size());
		}
		return notifications;
	}

	@Override
	public void deleteNotification(Long id) {
		if (!notificationRepository.existsById(id)) {
			logger.error("Notification not found with ID: {}", id);
			throw new NotificationNotFoundException("Notification not found with ID: " + id);
		}
		notificationRepository.deleteById(id);
		logger.info("Notification deleted with ID: {}", id);

	}

	@Override
	public Optional<Notification> updateNotification(Long id, Notification notification) {

		Optional<Notification> existingNotificationOpt = notificationRepository.findById(id);
		if (existingNotificationOpt.isEmpty()) {
			logger.error("Notification not found with ID: {}", id);
			throw new NotificationNotFoundException("Notification not found with ID: " + id);
		}
		return existingNotificationOpt;
	}

	@Override
	public void sendOtpEmail(UserEvent event) {

	    logger.info("Sending OTP email to: {}", event.getEmailId());

	    Notification notification = new Notification();
	    notification.setEmailId(event.getEmailId());
	    notification.setMessage("Your OTP is: 123456");
	    notification.setType(NotificationType.EMAIL);
	    notification.setRead(false);

	    try {
	        // ✅ REAL EMAIL
	        SimpleMailMessage mail = new SimpleMailMessage();
	        mail.setTo(event.getEmailId());
	        mail.setSubject("OTP Verification");
	        mail.setText("Your OTP is: 123456");

	        mailSender.send(mail);

	        notification.setType(NotificationType.SENT);
	        logger.info("OTP email sent successfully");

	    } catch (Exception e) {
	        notification.setType(NotificationType.FAILED);
	        logger.error("Failed to send OTP email", e);
	    }

	    notificationRepository.save(notification);
	}
	@Override
	public void sendWelcomeEmail(UserEvent event) {

	    logger.info("Sending welcome email to: {}", event.getEmailId());

	    Notification notification = new Notification();
	    notification.setEmailId(event.getEmailId());
	    notification.setMessage("Welcome to Branch Picking System!");
	    notification.setType(NotificationType.EMAIL);
	    notification.setRead(false);

	    try {
	        SimpleMailMessage mail = new SimpleMailMessage();
	        mail.setTo(event.getEmailId());
	        mail.setSubject("Welcome");
	        mail.setText("Your registration is successful. Welcome to Branch Picking System!");

	        mailSender.send(mail);

	        notification.setType(NotificationType.SENT);
	        logger.info("Welcome email sent successfully");

	    } catch (Exception e) {
	        notification.setType(NotificationType.FAILED);
       logger.error("Failed to send welcome email", e);
	    }

	    notificationRepository.save(notification);
	}}
