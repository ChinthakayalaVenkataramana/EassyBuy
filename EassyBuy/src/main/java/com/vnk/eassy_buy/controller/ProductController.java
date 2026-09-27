package com.vnk.eassy_buy.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vnk.eassy_buy.dto.ProductDto;
import com.vnk.eassy_buy.service.ProductService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {
	private final ProductService productService;
	
	@GetMapping("/home")
	public String homePage() {
		return "Welcome to the Home Page";
	}
	
	@PostMapping("/add_product")
	public String addProduct(@RequestBody ProductDto productDto) {
		
		return productService.addProduct(productDto);
	}
	
	@DeleteMapping("/delete_product/{id}")
	public String deleteProduct(@PathVariable Long id) {
		return productService.deleteProduct(id);
	}
	
	@PutMapping("/update/{id}")
	public String putMethodName(@PathVariable Long id, @RequestBody ProductDto productDto) {
		
		return productService.updateProduct(id, productDto);
	}
	
	@GetMapping("/get_product/{id}")
	public ProductDto getProduct(@PathVariable Long id ) {
		return productService.getProduct(id);
	}
	
	@GetMapping("/view_all_products")
	public List<ProductDto> viewAllProducts(){
		return productService.getAllProducts();
	}
	
}
