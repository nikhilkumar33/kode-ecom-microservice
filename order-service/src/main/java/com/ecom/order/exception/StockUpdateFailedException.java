package com.ecom.order.exception;

public class StockUpdateFailedException extends RuntimeException 
{
	public StockUpdateFailedException(String message)
	{
		super(message);
	}
}
