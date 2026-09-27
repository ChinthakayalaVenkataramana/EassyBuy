package com.vnk.eassy_buy.Entity;

import java.util.List;
import java.util.Map;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;
	private String productName;
	private String description;
	private String productModel;
	private Double productPrice;
	private Integer productDiscount;
	private String category;
	private String brand;
	private Integer stockQuantity;
	private Boolean active;
	@JdbcTypeCode(SqlTypes.JSON)
	private Map<String, List<String>> attributs;
}
