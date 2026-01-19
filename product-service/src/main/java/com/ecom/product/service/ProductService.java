package com.ecom.product.service;

import com.ecom.product.request.ProductRequest;
import com.ecom.product.response.ProductResponse;

public interface ProductService 
{
	public long createProduct(ProductRequest productRequest);

	public ProductResponse fetchProduct(long productId);
}
