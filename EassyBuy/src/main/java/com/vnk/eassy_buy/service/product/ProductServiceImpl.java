package com.vnk.eassy_buy.service.product;

import org.springframework.stereotype.Service;

import com.vnk.eassy_buy.Entity.Product;
import com.vnk.eassy_buy.dto.ProductDto;
import com.vnk.eassy_buy.repository.product.ProductRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
	private final ProductRepository productRepository;

	@Override
	public String addProduct(ProductDto productDto) {
		log.info("Adding new product");
		Product product = new Product();
		if (productDto == null) {
			log.warn("Unable to add product: Product details are empty");
			return "Product Unable to Add,Product Details Are Empty";
		} else {
			product.setProductId(product.getProductId());
			product.setActive(false);
			product.setAttributs(productDto.getAttributs());
			product.setBrand(productDto.getBrand());
			product.setCategory(productDto.getCategory());
			product.setDescription(productDto.getDescription());
			product.setProductDiscount(productDto.getProductDiscount());
			product.setStockQuantity(productDto.getStockQuantity());
			product.setProductPrice(productDto.getProductPrice());
			product.setProductName(productDto.getProductName());
			product.setProductModel(productDto.getProductModel());
			Product savedProduct = productRepository.save(product);
			log.info("Product added successfully with id={}", savedProduct.getProductId());
			return "Product added Successfully";
		}
	}

	@Override
	public String updateProduct(Long id, ProductDto productDto) {
		log.info("Updating product with id={}", id);
		if (productRepository.existsById(id)) {
			Product product = new Product();
			product.setProductId(id);
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
		log.warn("Product not found for update, id={}", id);
		return "Product Not Found To update";
	}

	@Override
	public String deleteProduct(Long id) {
		log.info("Deleting product with id={}", id);
		if (productRepository.existsById(id)) {
			productRepository.deleteById(id);
			log.info("Product deleted successfully, id={}", id);
			return " Product Deleted Successfully";
		}
		log.warn("Product not found for deletion, id={}", id);
		return "Product Not Found To delete";
	}

}
