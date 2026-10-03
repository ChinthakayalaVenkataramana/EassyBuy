package com.vnk.eassy_buy.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

	private Long productId;
	private String productName;
	private String description;
	private String productModel;
	private BigDecimal productPrice;
	private Integer productDiscount;
	private String category;
	private String brand;
	private Integer stockQuantity;
	private Map<String, List<String>> attributs;
}
