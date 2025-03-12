package com.vaishalitech.jrsnacks.service;

import java.util.List;

import com.vaishalitech.jrsnacks.entity.Category;
import com.vaishalitech.jrsnacks.entity.Product;

public interface ProductService {

	public Product addProduct(Product product);

	public List<Product> findAllProduct();

	public Product findByProductId(long productId);

	public Product updateByProductId(long productId, Product updateProduct);

	public Product deleteByProductId(long productId);

	public List<Product> findByCategoryId(Category categoryId);


}
