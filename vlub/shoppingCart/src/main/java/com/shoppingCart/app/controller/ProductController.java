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

import com.shoppingCart.app.exception.ProductNotFoundException;
import com.shoppingCart.app.model.Product;
import com.shoppingCart.app.service.ProductService;


@RestController
public class ProductController {

	@Autowired
	ProductService productService;
	
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Product>> getProducts(@RequestParam(value = "search", required = false) String search) throws ProductNotFoundException {
		List<Product> products;
		if (StringUtils.isEmpty(search))
			products = productService.findAll();
		else products = productService.findByString(search);
		return new ResponseEntity<List<Product>> (products, HttpStatus.FOUND);
	}
	
	@RequestMapping(value = "/products/{productId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Product> getProductDetail(@PathVariable("productId") Long productId) throws ProductNotFoundException  {
		Product product = productService.findById(productId);
		return new ResponseEntity<Product> (product, HttpStatus.OK);
	}

	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Void> addNewProduct(@RequestBody Product newProduct, HttpServletRequest request) throws URISyntaxException {
		Long newProductId = productService.save(newProduct);
		HttpHeaders header = new HttpHeaders();
		header.setLocation(new URI(request.getRequestURL() + "/" + newProductId.toString()));
		return new ResponseEntity<Void> (header, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/products/{productId}", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Void> updateProduct(@PathVariable("productId") Long productId, @RequestBody Product updateProduct, HttpServletRequest request) throws ProductNotFoundException, URISyntaxException  {
		Product product = productService.findById(productId);
		productService.update(product, updateProduct);
		HttpHeaders header = new HttpHeaders();
		header.setLocation(new URI(request.getRequestURL() + ""));
		return new ResponseEntity<Void> (header, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/products/{productId}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<Void> delete(@PathVariable("productId") Long productId, HttpServletRequest request) throws ProductNotFoundException, URISyntaxException {
		productService.delete(productId);
		HttpHeaders header = new HttpHeaders();
		header.setLocation(new URI(request.getRequestURL().substring(0, request.getRequestURL().indexOf("/" + productId))));
		return new ResponseEntity<Void> (header, HttpStatus.OK);
	}

}
