package com.ecom.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.product.entity.Categories;
import com.ecom.product.entity.Products;
import com.ecom.product.exception.InvalidProductIdException;
import com.ecom.product.repository.CategoriesRepository;
import com.ecom.product.repository.ProductRepository;
import com.ecom.product.request.ProductRequest;
import com.ecom.product.response.ProductResponse;

import jakarta.transaction.Transactional;

@Service
public class ProductServiceImplementation implements ProductService
{
	@Autowired
	ProductRepository productRepository;
	@Autowired
	CategoriesRepository categoriesRepository;
	
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
		
		return products.getProductId();
	}

	@Override
	public ProductResponse fetchProduct(long productId) {
		
		Products products = productRepository.findById(productId).orElseThrow( () -> 
													new InvalidProductIdException("No record found ! Invalid product id: "+productId));
		
		Categories categories = categoriesRepository.findByProductId(productId);
		
		ProductResponse response = new ProductResponse();
		response.setProductId(productId);
		response.setProductName(products.getProductName());
		response.setPrice(products.getPrice());
		response.setDescription(products.getDescription());
		response.setCategory(categories.getCategory());
		
		return response;
	}

}
