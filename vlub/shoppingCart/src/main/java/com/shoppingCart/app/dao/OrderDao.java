package com.shoppingCart.app.dao;

import java.util.List;

import com.shoppingCart.app.model.Order;

public interface OrderDao {

	void save(Order order);
	Order findById(Long orderId);
	List<Order> findAll();
	List<Order> findByString(String inputString);
	void update(Order order);
}
