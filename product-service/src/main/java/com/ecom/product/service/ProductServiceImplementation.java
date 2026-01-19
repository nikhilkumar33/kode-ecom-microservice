package com.ecom.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.product.client.InventoryClient;
import com.ecom.product.entity.Categories;
import com.ecom.product.entity.Products;
import com.ecom.product.exception.InvalidProductIdException;
import com.ecom.product.exception.InventoryStockFailedException;
import com.ecom.product.repository.CategoriesRepository;
import com.ecom.product.repository.ProductRepository;
import com.ecom.product.request.ProductRequest;
import com.ecom.product.request.StockRequest;
import com.ecom.product.response.InventoryResponse;
import com.ecom.product.response.ProductResponse;

import jakarta.transaction.Transactional;

@Service
public class ProductServiceImplementation implements ProductService
{
	@Autowired
	ProductRepository productRepository;
	@Autowired
	CategoriesRepository categoriesRepository;
	@Autowired
	InventoryClient inventoryClient;
	
	@Override
	@Transactional
	public long createProduct(ProductRequest productRequest) {
		
		Products products = new Products();
		products.setProductName(productRequest.getProductName());
		products.setPrice(productRequest.getPrice());
		products.setDescription(productRequest.getDescription());
		
		products = productRepository.save(products);
		
		Categories categories = new Categories();
		categories.setProductId(products.getProductId());
		categories.setCategory(productRequest.getCategory());
		
		categoriesRepository.save(categories);
		
		StockRequest request = new StockRequest();
		request.setProductId(products.getProductId());
		request.setProductName(products.getProductName());
		request.setStockQty(productRequest.getStockQty());
		String result = inventoryClient.createStock(request);
		if(result==null) {
			throw new InventoryStockFailedException("Failed to create stock.");
		}
		return products.getProductId();
	}

	@Override
	public ProductResponse fetchProduct(long productId) {
		
		Products products = productRepository.findById(productId).orElseThrow( () -> 
													new InvalidProductIdException("No record found ! Invalid product id: "+productId));
		
		Categories categories = categoriesRepository.findByProductId(productId);
		
		InventoryResponse  inventory = inventoryClient.getStock(productId);
		
		ProductResponse response = new ProductResponse();
		response.setProductId(productId);
		response.setProductName(products.getProductName());
		response.setPrice(products.getPrice());
		response.setDescription(products.getDescription());
		response.setCategory(categories.getCategory());
		response.setStockQty(inventory.getStockQty());
		
		return response;
	}

}
