package com.ecom.payment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.payment.request.PaymentRequest;
import com.ecom.payment.response.PaymentResponse;
import com.ecom.payment.service.PaymentService;

@RestController
public class PaymentController 
{
	private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
	@Autowired
	@Qualifier("upi")
	PaymentService paymentService;
	
	@PostMapping("/payments")
	public ResponseEntity<PaymentResponse> makePayment(@RequestBody PaymentRequest paymentRequest) {
		logger.info("Payment request received: {}",paymentRequest);
		PaymentResponse response = paymentService.processsPayment(paymentRequest);

		return ResponseEntity.ok(response);
	}
}
