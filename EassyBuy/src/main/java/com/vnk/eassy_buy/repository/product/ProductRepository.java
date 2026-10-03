package com.vnk.eassy_buy.repository.product;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vnk.eassy_buy.Entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	@Query("""
	        SELECT p
	        FROM Product p
	        WHERE
	            p.active = true

	            AND (
	                :search IS NULL
	                OR LOWER(p.productName) LIKE LOWER(CONCAT('%', :search, '%'))
	                OR LOWER(p.description) LIKE LOWER(CONCAT('%', :search, '%'))
	                OR LOWER(p.productModel) LIKE LOWER(CONCAT('%', :search, '%'))
	                OR LOWER(p.category) LIKE LOWER(CONCAT('%', :search, '%'))
	                OR LOWER(p.brand) LIKE LOWER(CONCAT('%', :search, '%'))
	            )

	            AND (:category IS NULL OR LOWER(p.category) = LOWER(:category))

	            AND (:brand IS NULL OR LOWER(p.brand) = LOWER(:brand))

	            AND (:minPrice IS NULL OR p.productPrice >= :minPrice)

	            AND (:maxPrice IS NULL OR p.productPrice <= :maxPrice)
	        """)
	Page<Product> searchProducts(
	        @Param("search") String search,
	        @Param("category") String category,
	        @Param("brand") String brand,
	        @Param("minPrice") BigDecimal minPrice,
	        @Param("maxPrice") BigDecimal maxPrice,
	        Pageable pageable
	);


}
