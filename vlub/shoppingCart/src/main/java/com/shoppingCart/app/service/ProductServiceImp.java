package com.shoppingCart.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shoppingCart.app.dao.ProductDao;
import com.shoppingCart.app.exception.ProductNotFoundException;
import com.shoppingCart.app.model.Product;

@Service
@Transactional
public class ProductServiceImp implements ProductService {

	@Autowired
	ProductDao productDao;

	@Override
	public Product findById(Long idProduct) throws ProductNotFoundException {
		Product product = productDao.findById(idProduct);
		if (product != null)
			return product;
		else
			throw new ProductNotFoundException();
	}

	@Override
	public List<Product> findByString(String inputString) throws ProductNotFoundException {
		List<Product> products = productDao.findByString(inputString);
		if (products.isEmpty() || products == null)
			throw new ProductNotFoundException();
		else
			return products;
	}

	@Override
	public List<Product> findAll() throws ProductNotFoundException {
		List<Product> products = productDao.findAll();
		if (products.isEmpty() || products == null)
			throw new ProductNotFoundException();
		else
			return products;
	}

	@Override
	public Long save(Product product) {
		return productDao.save(product);
	}

	@Override
	public void update(Product oldProduct, Product updateProduct) {
		oldProduct.setDescription(updateProduct.getDescription());
		oldProduct.setPrice(updateProduct.getPrice());
		productDao.update(oldProduct);
		
	}

	@Override
	public void delete(Long idProduct) throws ProductNotFoundException {
		Product product = productDao.findById(idProduct);
		if (product != null) {
			productDao.delete(product);
		} else throw new ProductNotFoundException();
	}

}
