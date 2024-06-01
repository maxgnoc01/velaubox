package com.shoppingCart.app.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shoppingCart.app.model.OrderDetail;

@Repository
public class OrderDetaildaoImp implements OrderDetailDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
	    return sessionFactory.getCurrentSession();
	  }

	@Override
	public void save(OrderDetail ordDetail) {
		getSession().save(ordDetail);
		
	}

}
