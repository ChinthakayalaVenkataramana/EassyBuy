package com.vnk.eassy_buy.service;

import java.util.List;

import com.vnk.eassy_buy.dto.ProductDto;

public interface ProductService {
	public String addProduct(ProductDto productDto);

	public String updateProduct(Long id, ProductDto productDto);

	public String deleteProduct(Long id);

	public ProductDto getProduct(Long productId);

	public List<ProductDto> getAllProducts();
}
