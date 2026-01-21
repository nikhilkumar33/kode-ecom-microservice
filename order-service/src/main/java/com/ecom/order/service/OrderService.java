package com.ecom.order.service;

import com.ecom.order.request.OrderRequest;
import com.ecom.order.response.OrderResponse;

public interface OrderService 
{
	public long createOrder(OrderRequest orderRequest);

	public OrderResponse getOrderDetail(long orderId);
}
