package com.ecom.user.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecom.user.response.ErrorResponse;

@RestControllerAdvice
public class UsersExceptionHandler {
	
	@ExceptionHandler(InvalidUserIdException.class)
	public ResponseEntity<ErrorResponse> handleInvalidUserIdException(InvalidUserIdException ex)
	{
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorCode("IUID-101");
		errorResponse.setErrorMessage(ex.getMessage());
		
		return ResponseEntity.ok(errorResponse);
	}

}
