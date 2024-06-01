package com.shoppingCart.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Order not found")
public class OrderNotFoundException extends Exception {
	
	public OrderNotFoundException() {
		super();
	}

	public OrderNotFoundException(String message) {
		super(message);
	}

}
