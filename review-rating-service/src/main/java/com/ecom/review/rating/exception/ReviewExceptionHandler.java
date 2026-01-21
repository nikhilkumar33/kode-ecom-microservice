package com.ecom.review.rating.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecom.review.rating.response.ErrorResponse;


@RestControllerAdvice
public class ReviewExceptionHandler {
	@ExceptionHandler(InvalidProductIdException.class)
	public ResponseEntity<ErrorResponse> handleInvalidProductIdException(InvalidProductIdException ex)
	{
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorCode("IPID-107");
		errorResponse.setErrorMessage(ex.getMessage());
		
		return ResponseEntity.ok(errorResponse);
	}
	
	@ExceptionHandler(NoProductFoundException.class)
	public ResponseEntity<ErrorResponse> handleNoProductFoundException(NoProductFoundException ex)
	{
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorCode("NPF-102");
		errorResponse.setErrorMessage(ex.getMessage());
		
		return ResponseEntity.ok(errorResponse);
	}
}
