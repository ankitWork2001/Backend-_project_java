package com.vaishalitech.jrsnacks.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.vaishalitech.jrsnacks.entity.Product;
import com.vaishalitech.jrsnacks.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> findByIsLatestTrue()
	{
		List<Product> products= productRepository.findByIsLatestTrue();
		if(products.isEmpty())
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No Product found");
		}else {
			return products;
		}
	}
	
	public void toggleLatestStatus(Long productId)
	{
		Optional<Product> product= productRepository.findById(productId);
		if(product.isEmpty())
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No Product found with this "+productId);
		}else {
			Product prod= product.get();
			prod.setIsLatest(!prod.getIsLatest());
			productRepository.save(prod);
		}
	}
}
