package com.ecom.payment.service;

import com.ecom.payment.request.PaymentRequest;
import com.ecom.payment.response.PaymentResponse;

public interface PaymentService 
{
	public PaymentResponse processsPayment(PaymentRequest paymentRequest);

}
