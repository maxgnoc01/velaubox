package com.shoppingCart.app.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shoppingCart.app.dao.OrderDao;
import com.shoppingCart.app.dao.OrderDetailDao;
import com.shoppingCart.app.dao.ProductDao;
import com.shoppingCart.app.exception.OrderNotFoundException;
import com.shoppingCart.app.exception.ProductNotEnoughException;
import com.shoppingCart.app.exception.ProductNotFoundException;
import com.shoppingCart.app.model.Order;
import com.shoppingCart.app.model.OrderDetail;
import com.shoppingCart.app.model.Product;
import com.shoppingCart.app.util.OrderStatus;

@Service
@Transactional
public class OrderServiceImp implements OrderService{

	@Autowired
	OrderDao orderDao;
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	OrderDetailDao ordDetailDao;
	
	@Override
	public Order findById(Long orderId) throws OrderNotFoundException {
		Order order = orderDao.findById(orderId);
		if (order != null) 
			return order;
		else throw new OrderNotFoundException();
	}

	@Override
	public List<Order> findAll() throws OrderNotFoundException {
		List<Order> ordersList = orderDao.findAll();
		if (ordersList == null || ordersList.isEmpty()) {
			throw new OrderNotFoundException();
		} else 
			return ordersList;
	}

	@Override
	public List<Order> findByString(String inputString) throws OrderNotFoundException {
		List<Order> ordersList = orderDao.findByString(inputString);
		if (ordersList == null || ordersList.isEmpty()) {
			throw new OrderNotFoundException();
		} else 
			return ordersList;
	}

	@Override
	public void save(Order order) throws ProductNotFoundException, ProductNotEnoughException{
		BigDecimal total = BigDecimal.ZERO;
		for(OrderDetail ordDetail : order.getListItem()) {
			Product checkProduct = productDao.findById(ordDetail.getProductId());
			if (checkProduct == null) throw new ProductNotFoundException();
//			ordDetailDao.save(ordDetail);
			if (ordDetail.getQuantity() > checkProduct.getIventoryNumber()) throw new ProductNotEnoughException();
			total = total.add(checkProduct.getPrice().multiply(new BigDecimal(ordDetail.getQuantity())));
			checkProduct.setIventoryNumber(checkProduct.getIventoryNumber() - ordDetail.getQuantity());
			productDao.update(checkProduct);
			ordDetail.setOrder(order);
		}
		order.setTotal(total);
		order.setStatus(OrderStatus.NEW);
		order.setOrderedDate(new Date());
		orderDao.save(order);
	}

	@Override
	public void update(Long orderId, String newStatus) throws Exception{
		Order order = findById(orderId);
		if(!EnumUtils.isValidEnum(OrderStatus.class, newStatus.toUpperCase()))
			throw new Exception("Not a true status");
		else {
			order.setStatus(Enum.valueOf(OrderStatus.class, newStatus.toUpperCase()));
		}
		orderDao.update(order);
	}

}
