package com.ecom.inventory.service;

import com.ecom.inventory.request.StockRequest;
import com.ecom.inventory.request.UpdateRequest;
import com.ecom.inventory.response.InventoryResponse;
import com.ecom.inventory.response.ProductResponse;

public interface InventoryService {

	public long createStock(StockRequest stockRequest);

	public InventoryResponse getStock(long productId);

	public ProductResponse fetchProduct(long productId);

	public int updateProductStock(UpdateRequest request);
}
