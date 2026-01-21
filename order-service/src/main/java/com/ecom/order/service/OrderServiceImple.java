package com.ecom.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.order.client.InventoryClient;
import com.ecom.order.client.PaymentClient;
import com.ecom.order.client.ProductClient;
import com.ecom.order.entity.OrderItems;
import com.ecom.order.entity.Orders;
import com.ecom.order.exception.OrderNotFoundException;
import com.ecom.order.exception.PaymentFailedException;
import com.ecom.order.exception.ProductNotFoundException;
import com.ecom.order.exception.StockUpdateFailedException;
import com.ecom.order.kafka.KafkaOrderProducer;
import com.ecom.order.repository.OrderItemsRepository;
import com.ecom.order.repository.OrderRepository;
import com.ecom.order.request.OrderRequest;
import com.ecom.order.request.PaymentRequest;
import com.ecom.order.request.StockUpdateRequest;
import com.ecom.order.response.OrderResponse;
import com.ecom.order.response.PaymentResponse;
import com.ecom.order.response.ProductResponse;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImple implements OrderService
{
	@Autowired
	ProductClient productClient;
	@Autowired
	PaymentClient paymentClient;
	@Autowired
	InventoryClient inventoryClient;
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	OrderItemsRepository orderItemsRepository;
	@Autowired
	KafkaOrderProducer kafkaOrderProducer;
	
	@Override
	@Transactional
	public long createOrder(OrderRequest request) {
		
		ProductResponse response=productClient.fetchProduct(request.getProductId());
		if(response.getProductName()==null) {
			throw new ProductNotFoundException("No product found. Product id: "+request.getProductId());
		}
		Orders orders = new Orders();
		orders.setUserId(request.getUserId());
		orders.setTotalQuantity(request.getQuantity());
		int totalAmount = request.getQuantity() * response.getPrice();
		orders.setTotalAmount(totalAmount);
		
		orders = orderRepository.save(orders);
		
		OrderItems orderItems = new OrderItems();
		orderItems.setOrderId(orders.getOrderId());
		orderItems.setProductId(response.getProductId());
		orderItems.setProductName(response.getProductName());
		orderItems.setPrice(response.getPrice());
		orderItems.setQuantity(request.getQuantity());
		orderItems.setItemTotal(totalAmount);
		
		orderItems = orderItemsRepository.save(orderItems);
		
		PaymentRequest paymentRequest = new PaymentRequest();
		paymentRequest.setAmount(totalAmount);
		paymentRequest.setOrderId(orders.getOrderId());
		paymentRequest.setPaymentType("upi");
		PaymentResponse paymentResponse= paymentClient.makePayment(paymentRequest);
		String paymentStatus = paymentResponse.getStatus();
		if(!paymentStatus.equals("SUCCESS")) {
			throw new PaymentFailedException("Payment is failed.. Payment status: "+paymentStatus);
		}
		
		orders.setOrderStatus(paymentStatus);
		orders = orderRepository.save(orders);
		
		StockUpdateRequest updateRequest = new StockUpdateRequest();
		updateRequest.setProductid(response.getProductId());
		updateRequest.setReduceStockQty(request.getQuantity());
		
		String message = inventoryClient.updateStock(updateRequest);
		if(message==null) {
			throw new StockUpdateFailedException("Unable to update stock...");
		}
		
		kafkaOrderProducer.sendMessage("Order Created Successfully.. Your Order id is: "+orders.getOrderId());
		return orders.getOrderId();
	}

	@Override
	public OrderResponse getOrderDetail(long orderId) {
		Orders orders = orderRepository.findById(orderId).orElseThrow( () ->
					new OrderNotFoundException("No record found for order id: "+orderId));
		
		OrderItems orderItems = orderItemsRepository.findByOrderId(orders.getOrderId());
		
		OrderResponse response = new OrderResponse();
		response.setProductId(orderItems.getProductId());
		response.setProductName(orderItems.getProductName());
		response.setProductPrice(orderItems.getPrice());
		response.setTotalQuantity(orders.getTotalQuantity());
		response.setTotalAmount(orders.getTotalAmount());
		
		return response;
	}

}
