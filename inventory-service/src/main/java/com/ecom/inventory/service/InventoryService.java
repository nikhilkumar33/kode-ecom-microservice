package com.ecom.inventory.service;

import com.ecom.inventory.request.StockRequest;

public interface InventoryService {

	public long createStock(StockRequest stockRequest);
}
