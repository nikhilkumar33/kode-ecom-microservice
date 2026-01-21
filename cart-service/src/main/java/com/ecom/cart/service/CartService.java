package com.ecom.cart.service;

import com.ecom.cart.request.CartRequest;
import com.ecom.cart.request.RemoveRequest;

public interface CartService {

	long addToCart(CartRequest cartRequest);

	void removeCartProduct(RemoveRequest removeRequest);

}
