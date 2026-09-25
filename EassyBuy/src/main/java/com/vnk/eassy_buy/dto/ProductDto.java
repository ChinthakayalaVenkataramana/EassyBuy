package com.vnk.eassy_buy.dto;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
	private String productName;
	private String description;
	private String productModel;
	private Double productPrice;
	private Integer productDiscount;
	private String category;
	private String brand;
	private Integer stockQuantity;
	private Boolean active;
	private Map<String, List<String>> attributs;
}
