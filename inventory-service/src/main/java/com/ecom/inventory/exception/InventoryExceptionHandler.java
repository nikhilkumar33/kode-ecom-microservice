package com.ecom.inventory.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecom.inventory.response.ErrorResponse;

@RestControllerAdvice
public class InventoryExceptionHandler {
	
	@ExceptionHandler(InvalidProductIdException.class)
	public ResponseEntity<ErrorResponse> handleInvalidProductIdException(InvalidProductIdException ex)
	{
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorCode("IPID-105");
		errorResponse.setErrorMessage(ex.getMessage());
		
		return ResponseEntity.ok(errorResponse);
	}
}
