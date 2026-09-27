package com.vnk.eassy_buy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vnk.eassy_buy.Entity.Product;
public interface ProductRepository extends JpaRepository<Product, Long>{

}
