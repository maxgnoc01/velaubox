package com.shoppingCart.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Not enough quantity in iventory")
public class ProductNotEnoughException extends Exception {
	public ProductNotEnoughException() {
		super();
	}
	public ProductNotEnoughException(String message) {
		super(message);
	}

}
