package com.ecom.user.controller;

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

import com.ecom.user.request.UsersRequest;
import com.ecom.user.response.UsersDetails;
import com.ecom.user.service.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController 
{
	private static final Logger logger = LoggerFactory.getLogger(UsersController.class);
	
	@Autowired
	UsersService usersService;
	
	@PostMapping
	public ResponseEntity<String> createUser(@RequestBody UsersRequest usersRequest)
	{
		logger.info("Create user request received: {}",usersRequest);
		
		long userId = usersService.createUser(usersRequest);
		
		return ResponseEntity.ok("Registation successfully done. Your user id is: "+userId);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UsersDetails> getUserDetails(@PathVariable long userId)
	{
		logger.info("Get user request received: {}",userId);
		UsersDetails usersDetails = usersService.getUserDetails(userId);
		return ResponseEntity.ok(usersDetails);
	}
}
