package com.ecom.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.user.request.UsersRequest;

@RestController
public class UsersController {

	
	@PostMapping("/users")
	public ResponseEntity createUser(@RequestBody UsersRequest usersRequest)
	{
		return ResponseEntity.ok(null);
	}
}
