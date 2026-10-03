package com.vnk.eassy_buy.service.product;

import com.vnk.eassy_buy.dto.ProductDto;

public interface ProductService {
	public String addProduct(ProductDto productDto);

	public String updateProduct(Long id, ProductDto productDto);

	public String deleteProduct(Long id);

}
