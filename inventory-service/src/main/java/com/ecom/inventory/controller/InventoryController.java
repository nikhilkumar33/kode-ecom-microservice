package com.ecom.inventory.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.inventory.request.StockRequest;
import com.ecom.inventory.request.StockUpdateRequest;
import com.ecom.inventory.response.InventoryResponse;
import com.ecom.inventory.response.ProductResponse;
import com.ecom.inventory.service.InventoryService;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
	private static final Logger logger = LoggerFactory.getLogger(InventoryController.class);
	@Autowired
	InventoryService inventoryService;
	
	@PostMapping
	public ResponseEntity<String> createStock(@RequestBody StockRequest stockRequest)
	{
		logger.info("Create stock request received: {}",stockRequest);
		long inventoryId = inventoryService.createStock(stockRequest);
		return ResponseEntity.ok("Stock created successfully. Inventory id is: "+inventoryId);
	}
	
	@GetMapping("/getInventory/{productId}")
	public ResponseEntity<InventoryResponse> getStock(@PathVariable long productId)
	{
		logger.info("Get stock request received: {}",productId);
		InventoryResponse response = inventoryService.getStock(productId);
		return ResponseEntity.ok(response);
	}
	@GetMapping("/{productId}")
	public ResponseEntity<ProductResponse> fetchProduct(@PathVariable long productId)
	{
		logger.info("Fetch product request received: {}",productId);
		ProductResponse productResponse = inventoryService.fetchProduct(productId);
		return ResponseEntity.ok(productResponse);
	}
	
	@PutMapping("/reduce")
	public String updateStock(@RequestBody StockUpdateRequest request)
	{
		logger.info("Reduce product stock request received: {}",request);
		int newStock = inventoryService.updateProductStock(request);
		return "Product stock updated. New stock is: "+newStock;
	}
}
