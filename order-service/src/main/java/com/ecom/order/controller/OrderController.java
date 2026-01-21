package com.ecom.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.order.request.OrderRequest;
import com.ecom.order.response.OrderResponse;
import com.ecom.order.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController 
{
	@Autowired
	OrderService orderService;
	
	@PostMapping
	public ResponseEntity<String> placeOrder(@RequestBody OrderRequest orderRequest)
	{
		long orderId = orderService.createOrder(orderRequest);
		return ResponseEntity.ok("Order Placed successfully. Order id is: "+orderId);
	}
	
	@GetMapping("{orderId}")
	public ResponseEntity<OrderResponse> getOrderDetails(@PathVariable long orderId)
	{
		OrderResponse response = orderService.getOrderDetail(orderId);
		return ResponseEntity.ok(response);
	}
}
