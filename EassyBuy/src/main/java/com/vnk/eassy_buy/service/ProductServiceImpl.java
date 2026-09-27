package com.vnk.eassy_buy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vnk.eassy_buy.Entity.Product;
import com.vnk.eassy_buy.dto.ProductDto;
import com.vnk.eassy_buy.repository.ProductRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
	private final ProductRepository productRepository;

	@Override
	public String addProduct(ProductDto productDto) {
		Product product = new Product();
		if (productDto == null) {
			return "Product Unable to Add,Product Details Are Empty";
		} else {
			product.setActive(productDto.getActive());
			product.setAttributs(productDto.getAttributs());
			product.setBrand(productDto.getBrand());
			product.setCategory(productDto.getCategory());
			product.setDescription(productDto.getDescription());
			product.setProductDiscount(productDto.getProductDiscount());
			product.setStockQuantity(productDto.getStockQuantity());
			product.setProductPrice(productDto.getProductPrice());
			product.setProductName(productDto.getProductName());
			product.setProductModel(productDto.getProductModel());
			productRepository.save(product);
			return "Product added Successfully";
		}
	}

	@Override
	public String updateProduct(Long id, ProductDto productDto) {
		if(productRepository.existsById(id)){
			
			Product product = new Product();
			product.setProductId(id);
			product.setActive(productDto.getActive());
			product.setAttributs(productDto.getAttributs());
			product.setBrand(productDto.getBrand());
			product.setCategory(productDto.getCategory());
			product.setDescription(productDto.getDescription());
			product.setProductDiscount(productDto.getProductDiscount());
			product.setStockQuantity(productDto.getStockQuantity());
			product.setProductPrice(productDto.getProductPrice());
			product.setProductName(productDto.getProductName());
			product.setProductModel(productDto.getProductModel());
			productRepository.save(product);
		return " Product Updated Successfully";
		}
		return "Product Not Found To update";
	}

	@Override
	public String deleteProduct(Long id) {
		if(productRepository.existsById(id)){
			productRepository.deleteById(id);
			return " Product Deleted Successfully";
		}
		return "Product Not Found To delete";
	}

	@Override
	public ProductDto getProduct(Long productId) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("Product not found"));

		return new ProductDto(product.getProductName(), product.getDescription(), product.getProductModel(),
				product.getProductPrice(), product.getProductDiscount(), product.getCategory(), product.getBrand(),
				product.getStockQuantity(), product.getActive(), product.getAttributs());
	}

	@Override
	public List<ProductDto> getAllProducts() {
		List<Product> allProducts = productRepository.findAll();
		return allProducts.stream()
				.map(product -> new ProductDto(product.getProductName(), product.getDescription(),
						product.getProductModel(), product.getProductPrice(), product.getProductDiscount(),
						product.getCategory(), product.getBrand(), product.getStockQuantity(), product.getActive(),
						product.getAttributs()))
				.toList();
	}

}
