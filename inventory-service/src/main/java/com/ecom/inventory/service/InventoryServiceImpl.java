package com.ecom.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.inventory.entity.Inventory;
import com.ecom.inventory.repository.InventoryRepository;
import com.ecom.inventory.request.StockRequest;

@Service
public class InventoryServiceImpl implements InventoryService
{
	@Autowired
	InventoryRepository inventoryRepository;
	@Override
	public long createStock(StockRequest stockRequest) 
	{
		Inventory inventory = new Inventory();
		inventory.setProductId(stockRequest.getProductId());
		inventory.setProductName(stockRequest.getProductName());
		inventory.setStockQty(stockRequest.getStockQty());
		
		inventory = inventoryRepository.save(inventory);
		
		return inventory.getInventoryId();
	}

}
