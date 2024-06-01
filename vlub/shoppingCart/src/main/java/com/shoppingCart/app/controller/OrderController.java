package com.shoppingCart.app.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingCart.app.exception.OrderNotFoundException;
import com.shoppingCart.app.exception.ProductNotEnoughException;
import com.shoppingCart.app.exception.ProductNotFoundException;
import com.shoppingCart.app.model.Order;
import com.shoppingCart.app.service.OrderService;

@RestController
public class OrderController {
	@Autowired
	OrderService orderService;
	
	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Order>> getOrders(@RequestParam(value = "search", required = false) String search) throws OrderNotFoundException {
		List<Order> orderList;
		if (StringUtils.isEmpty(search)) {
			orderList = orderService.findAll();
		} else {
			orderList = orderService.findByString(search);
		}
		return new ResponseEntity<List<Order>> (orderList, HttpStatus.FOUND);
	}
	
	@RequestMapping(value = "/orders/{orderId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Order> getOrderDetail(@PathVariable("orderId") Long orderId)  throws OrderNotFoundException {
		Order order = orderService.findById(orderId);
		return new ResponseEntity<Order> (order, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/orders", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Void> addNewOrders(@RequestBody Order order, HttpServletRequest request) throws URISyntaxException, ProductNotFoundException, ProductNotEnoughException {
		orderService.save(order);
		HttpHeaders header = new HttpHeaders();
		header.setLocation(new URI(request.getRequestURL() + "/"));
		return new ResponseEntity<Void> (header, HttpStatus.CREATED);
	}
	@RequestMapping(value = "/orders/{orderId}", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Void> updateOrder(@PathVariable("orderId") Long orderId, @RequestParam(value = "status") String newStatus, HttpServletRequest request) throws Exception {
		orderService.update(orderId, newStatus);
		HttpHeaders header = new HttpHeaders();
		header.setLocation(new URI(request.getRequestURL()+""));
		return new ResponseEntity<Void> (header, HttpStatus.OK);
	}

}
