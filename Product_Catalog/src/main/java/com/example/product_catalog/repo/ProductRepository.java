package com.example.product_catalog.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.product_catalog.model.ProductDTO;

public interface ProductRepository extends JpaRepository<ProductDTO, Long> {

}
