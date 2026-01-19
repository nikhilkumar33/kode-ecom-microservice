package com.ecom.product.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ecom.product.request.StockRequest;

@FeignClient(name="payment-service")
public interface InventoryClient {
	
	@PostMapping("/inventory")
	String createStock(@RequestBody StockRequest stockRequest);
	
}
