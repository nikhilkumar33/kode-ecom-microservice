package com.ecom.product.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecom.product.response.ErrorResponse;

@RestControllerAdvice
public class ProductExceptionHandler {
	
	@ExceptionHandler(InvalidProductIdException.class)
	public ResponseEntity<ErrorResponse> handleInvalidProductIdException(InvalidProductIdException ex)
	{
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorCode("IPID-102");
		errorResponse.setErrorMessage(ex.getMessage());
		
		return ResponseEntity.ok(errorResponse);
	}
	
	@ExceptionHandler(InventoryStockFailedException.class)
	public ResponseEntity<ErrorResponse> handleInventoryStockFailedException(InventoryStockFailedException ex)
	{
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorCode("ISF-106");
		errorResponse.setErrorMessage(ex.getMessage());
		
		return ResponseEntity.ok(errorResponse);
	}
}
