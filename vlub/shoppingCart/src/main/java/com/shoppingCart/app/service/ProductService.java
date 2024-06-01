package com.shoppingCart.app.service;

import java.util.List;

import com.shoppingCart.app.exception.ProductNotFoundException;
import com.shoppingCart.app.model.Product;

public interface ProductService {

	Product findById(Long idProduct) throws ProductNotFoundException;
	List<Product> findByString(String inputString) throws ProductNotFoundException;
	List<Product> findAll() throws ProductNotFoundException;
	Long save(Product product);
	void update(Product oldProduct, Product updateProduct);
	void delete(Long idProduct) throws ProductNotFoundException;
}
