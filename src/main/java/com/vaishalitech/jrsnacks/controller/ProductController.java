package com.vaishalitech.jrsnacks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaishalitech.jrsnacks.entity.Category;
import com.vaishalitech.jrsnacks.entity.Product;
import com.vaishalitech.jrsnacks.service.ProductService;
import com.vaishalitech.jrsnacks.utility.ResponseStructure;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Product>> addProduct(@RequestBody Product product){
		Product product2 = productService.addProduct(product);
		ResponseStructure<Product> responseStructure = new ResponseStructure<Product>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Product object Created successfully");
		responseStructure.setData(product2);
		return new ResponseEntity<ResponseStructure<Product>>(responseStructure, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Product>>> findAllProduct(){
		List<Product> products = productService.findAllProduct();
		ResponseStructure<List<Product>> responseStructure = new ResponseStructure<List<Product>>();
		responseStructure.setStatusCode(HttpStatus.FOUND.value());
		responseStructure.setMessage("Product object fond successfully");
		responseStructure.setData(products);
		return new ResponseEntity<ResponseStructure<List<Product>>>(responseStructure, HttpStatus.FOUND);
		
	}
	
	@GetMapping("/id")
	public ResponseEntity<ResponseStructure<Product>> findByProductId(long productId) {
		Product product = productService.findByProductId(productId);
		ResponseStructure<Product> responseStructure = new ResponseStructure<Product>();
		responseStructure.setStatusCode(HttpStatus.FOUND.value());
		responseStructure.setMessage("Product object found with the id");
		responseStructure.setData(product);
		return new ResponseEntity<ResponseStructure<Product>>(responseStructure, HttpStatus.FOUND);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Product>> updateByProductId(long productId, @RequestBody Product updateProduct) {
		Product product = productService.updateByProductId(productId, updateProduct);
		ResponseStructure<Product> responseStructure = new ResponseStructure<Product>();
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Product updated successfully");
		responseStructure.setData(product);
		return new ResponseEntity<ResponseStructure<Product>>(responseStructure, HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Product>> deleteByProductId(long productId) {
		Product product = productService.deleteByProductId(productId);
		ResponseStructure<Product> responseStructure = new ResponseStructure<Product>();
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Product object deleted Successfully");
		responseStructure.setData(product);
		return new ResponseEntity<ResponseStructure<Product>>(responseStructure, HttpStatus.OK);
	}
	
	@GetMapping("/category/id")
	public ResponseEntity<ResponseStructure<List<Product>>> findByCategoryId(Category categoryId){
		List<Product> products = productService.findByCategoryId(categoryId);
		ResponseStructure<List<Product>> responseStructure = new ResponseStructure<List<Product>>();
		responseStructure.setStatusCode(HttpStatus.FOUND.value());
		responseStructure.setMessage("Product object fond successfully");
		responseStructure.setData(products);
		return new ResponseEntity<ResponseStructure<List<Product>>>(responseStructure, HttpStatus.FOUND);
		
	}
}
