package com.shoppingCart.app.dao;

import java.util.List;

import com.shoppingCart.app.model.Product;

public interface ProductDao {

	Product findById(Long productId);
	List<Product> findByString(String inputString);
	List<Product> findAll();
	Long save(Product newProduct);
	void update(Product product);
	void delete(Product product);
}
