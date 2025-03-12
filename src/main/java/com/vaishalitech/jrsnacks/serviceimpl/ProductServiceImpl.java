package com.vaishalitech.jrsnacks.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaishalitech.jrsnacks.entity.Category;
import com.vaishalitech.jrsnacks.entity.Product;
import com.vaishalitech.jrsnacks.repository.ProductRepository;
import com.vaishalitech.jrsnacks.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService  {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product addProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public List<Product> findAllProduct() {
		List<Product> products = productRepository.findAll();
		if(products.isEmpty()) {
			return null;
		}else {
			return products;
		}
	}

	@Override
	public Product findByProductId(long productId) {
		Optional<Product> optional = productRepository.findById(productId);

		if(optional.isEmpty()) {
			return null;
		}else {
			Product product = optional.get();
			return product;
		}
	}

	@Override
	public Product updateByProductId(long productId, Product updateProduct) {
		Optional<Product> optional = productRepository.findById(productId);

		if(optional.isEmpty()) {
			return null;
		}else {
			Product existingProduct = optional.get();
			updateProduct.setId(existingProduct.getId());
			return productRepository.save(updateProduct);
		}
	}

	@Override
	public Product deleteByProductId(long productId) {
		Optional<Product> optional = productRepository.findById(productId);

		if(optional.isEmpty()) {
			return null;
		}else {
			Product product = optional.get();
			productRepository.delete(product);
			return product;
		}
	}

	@Override
	public List<Product> findByCategoryId(Category categoryId) {
		List<Product> products = productRepository.findByCategoryId(categoryId);

		if(products.isEmpty()) {
			return null;
		}else {
			return products;
		}
	}
	
	
}
