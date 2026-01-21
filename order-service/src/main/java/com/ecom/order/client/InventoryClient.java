package com.ecom.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ecom.order.request.StockUpdateRequest;

@FeignClient(name ="inventory-service")
public interface InventoryClient 
{
	@PutMapping("/inventory/reduce")
	public String updateStock(@RequestBody StockUpdateRequest request);
}
