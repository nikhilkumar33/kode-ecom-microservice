package com.ecom.product.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.product.request.ProductRequest;
import com.ecom.product.response.ProductResponse;
import com.ecom.product.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController
{
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	ProductService productService;
	
	@PostMapping
	public ResponseEntity<String> addProduct(@RequestBody ProductRequest productRequest)
	{
		logger.info("Create product request received: {}",productRequest);
		
		long productId = productService.createProduct(productRequest);
		return ResponseEntity.ok("Product added successfully. Product is is: "+productId);
	}
	
	@GetMapping("/{productId}")
	public ResponseEntity<ProductResponse> fetchProduct(@PathVariable long productId)
	{
		logger.info("Fetch product request received: {}",productId);

		ProductResponse productResponse = productService.fetchProduct(productId);
		return ResponseEntity.ok(productResponse);
	}
}
