package com.ecom.review.rating.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecom.review.rating.response.ProductResponse;

@FeignClient(name ="product-service")
public interface ProductClient {
	
	@GetMapping("/products/{productId}")
	ProductResponse fetchProduct(@PathVariable long productId);

}
