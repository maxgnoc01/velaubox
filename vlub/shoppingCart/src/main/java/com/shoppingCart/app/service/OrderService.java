package com.shoppingCart.app.service;

import java.util.List;

import com.shoppingCart.app.exception.OrderNotFoundException;
import com.shoppingCart.app.exception.ProductNotEnoughException;
import com.shoppingCart.app.exception.ProductNotFoundException;
import com.shoppingCart.app.model.Order;

public interface OrderService {
	
	Order findById(Long orderId) throws OrderNotFoundException;
	List<Order> findAll() throws OrderNotFoundException;
	List<Order> findByString(String inputString) throws OrderNotFoundException;
	void save(Order order) throws ProductNotFoundException, ProductNotEnoughException;
	void update(Long orderId, String newStatus) throws Exception;
}
