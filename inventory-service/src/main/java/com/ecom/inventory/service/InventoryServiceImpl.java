package com.ecom.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.inventory.client.ProductClient;
import com.ecom.inventory.entity.Inventory;
import com.ecom.inventory.exception.InvalidProductIdException;
import com.ecom.inventory.repository.InventoryRepository;
import com.ecom.inventory.request.StockRequest;
import com.ecom.inventory.request.StockUpdateRequest;
import com.ecom.inventory.response.InventoryResponse;
import com.ecom.inventory.response.ProductResponse;

@Service
public class InventoryServiceImpl implements InventoryService
{
	@Autowired
	InventoryRepository inventoryRepository;
	@Autowired
	ProductClient productClient;
	
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
	@Override
	public InventoryResponse getStock(long productId) {
		
		Inventory inventory=inventoryRepository.findByProductId(productId);
		InventoryResponse response = new InventoryResponse();
		response.setInventoryId(inventory.getInventoryId());
		response.setStockQty(inventory.getStockQty());
		
		return response;
		
	}
	@Override
	public ProductResponse fetchProduct(long productId) {
		ProductResponse productResponse =  productClient.fetchProduct(productId);
		return productResponse;
	}
	@Override
	public int updateProductStock(StockUpdateRequest request) {
		Inventory inventory = inventoryRepository.findByProductId(request.getProductid());
		if(inventory==null) {
			throw new InvalidProductIdException("No element found! Product id: "+request.getProductid());
		}
		int newStock = inventory.getStockQty() - request.getReduceStockQty();
		inventory.setStockQty(newStock);
		inventory = inventoryRepository.save(inventory);
		
		return inventory.getStockQty();
	}

}
