package com.shoppingCart.app.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shoppingCart.app.model.Product;

@Repository
public class ProductDaoImp implements ProductDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
	    return sessionFactory.getCurrentSession();
	  }
	
	@Override
	public Product findById(Long productId) {
		String queryString = "SELECT p FROM Product AS p WHERE p.productId = :productId";
		return (Product) getSession().createQuery(queryString)
								.setParameter("productId", productId)
								.uniqueResult();
	}

	@Override
	public List<Product> findAll() {
		String queryString = "SELECT p FROM Product AS p";
		return getSession().createQuery(queryString)
					.list();
	}

	@Override
	public List<Product> findByString(String inputString) {
		String queryString = "SELECT p FROM Product AS p WHERE p.productName LIKE :string OR p.description LIKE  :string";
		return getSession().createQuery(queryString).setParameter("string", "%" + inputString + "%").list();
	}

	@Override
	public Long save(Product newProduct) {
		return (Long) sessionFactory.getCurrentSession().save(newProduct);
	}

	@Override
	public void update(Product product) {
		sessionFactory.getCurrentSession().update(product);
		
	}

	@Override
	public void delete(Product product) {
		sessionFactory.getCurrentSession().delete(product);
		
	}

}
