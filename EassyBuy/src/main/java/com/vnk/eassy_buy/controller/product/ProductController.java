package com.vnk.eassy_buy.controller.product;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vnk.eassy_buy.dto.ProductDto;
import com.vnk.eassy_buy.service.product.ProductService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {
	private final ProductService productService;

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

}
