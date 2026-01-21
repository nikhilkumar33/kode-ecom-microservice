package com.ecom.notification.service;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

	public void sendPaymentSuccessEmail(String message) {
		System.out.println("Sending payment success email: " + message);
	}

	public void sendPaymentFailedEmail(String message) {
		System.out.println("Sending payment failed email: " + message);
	}

	public void sendOrderCreatedEmail(String message) {
		System.out.println("Sending order created email: " + message);
	}

}
