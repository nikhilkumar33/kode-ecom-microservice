package com.ecom.product.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ecom.product.exception.InventoryStockFailedException;
import com.ecom.product.request.StockRequest;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@FeignClient(name="inventory-service")
public interface InventoryClient {
	
	@CircuitBreaker(name = "inventoryServiceCB", fallbackMethod = "fallbackInventory")
	@Retry(name = "inventoryServiceRetry")
	@PostMapping("/inventory")
	String createStock(@RequestBody StockRequest stockRequest);
	
	
	default String fallbackInventory(StockRequest stockRequest, Throwable ex) {
		// this will get triggred / executed if CB executes.
		System.out.println("Inside Inventory client fallback method...");
		System.out.println(ex.getMessage());
		
		throw new InventoryStockFailedException("Failed to create stock.");
	}
}
