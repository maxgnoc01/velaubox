package com.shoppingCart.app.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shoppingCart.app.model.Order;

@Repository
public class OrderDaoImp implements OrderDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
	    return sessionFactory.getCurrentSession();
	  }

	@Override
	public void save(Order order) {
		getSession().persist(order);	
	}

	@Override
	public Order findById(Long orderId) {
		String queryString = "SELECT od FROM Order AS od WHERE od.orderId = :orderId";
		return (Order) getSession().createQuery(queryString)
				.setParameter("orderId", orderId)
				.uniqueResult();
	}

	@Override
	public List<Order> findAll() {
		String queryString = "SELECT od FROM Order AS od";
		return getSession().createQuery(queryString)
				.list();
	}

	@Override
	public List<Order> findByString(String inputString) {
		String queryString = "SELECT od FROM Order AS od WHERE od.customerName LIKE :string" + (isNumberic(inputString)?" OR od.orderId like :string":"");
		return getSession().createQuery(queryString).setParameter("string", "%" + inputString + "%").list();
	}
	
	private boolean isNumberic(String string) {
		try {
			Long.parseLong(string);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	@Override
	public void update(Order order) {
		getSession().update(order);
	}

}
