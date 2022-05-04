package es.codeurjc.shop.notifications.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.shop.notifications.dto.NotificationDto;
import es.codeurjc.shop.notifications.service.NotificationService;

@RestController
class NotificationController {

	@Autowired
	private NotificationService notificationService;

	@PostMapping("/notifications")
	ResponseEntity<String> createCustomer(@RequestBody NotificationDto notification) {
		notificationService.sendEmailNotification(notification);
		return ResponseEntity.ok().body("Notifications created successfully");
	}

}