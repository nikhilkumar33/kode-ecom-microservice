package com.ecom.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.cart.request.CartRequest;
import com.ecom.cart.request.RemoveRequest;
import com.ecom.cart.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	CartService cartService;
	
	@PostMapping("/add")
	public String addProductToCart(@RequestBody CartRequest cartRequest)
	{
		long cartId = cartService.addToCart(cartRequest);
		
		return "Item successfully added to cart. Cart id is: "+cartId;
	}
	
	@DeleteMapping("/remove")
	public ResponseEntity deleteCartItem(@RequestBody RemoveRequest removeRequest)
	{
		cartService.removeCartProduct(removeRequest);
		return ResponseEntity.ok("Item Removed Successfully.");
	}
}
