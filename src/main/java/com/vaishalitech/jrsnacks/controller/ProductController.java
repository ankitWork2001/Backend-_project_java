package com.vaishalitech.jrsnacks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.vaishalitech.jrsnacks.entity.Product;
import com.vaishalitech.jrsnacks.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/latest")
	public ResponseEntity<?> findByIsLatestTrue()
	{
		try {
		List<Product> products = productService.findByIsLatestTrue();
		return new ResponseEntity<>(products,HttpStatus.OK);
		}catch(ResponseStatusException e)
		{
			return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
		}
	}
	
	
	@PutMapping("/{id}/latest")
	public ResponseEntity<?> toggleLatestStatus(@PathVariable Long id)
	{
		try {
			productService.toggleLatestStatus(id);
			return new ResponseEntity<>("Latest Collection Status Updated Successfully!!",HttpStatus.OK);
		}catch(ResponseStatusException e)
		{
			return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
		}
	}
	
	
}
