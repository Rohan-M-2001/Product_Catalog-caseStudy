package com.example.product_catalog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.product_catalog.model.ProductDTO;
import com.example.product_catalog.repo.ProductRepository;

@Service
public class ProductService {
  @Autowired
  private ProductRepository productRepository;

  @Transactional(readOnly = true)
  public List<ProductDTO> getAllProducts() {

    return this.productRepository.findAll();
  }

  @Transactional(readOnly = true)
  public ProductDTO getProductById(Long id) {

    return this.productRepository.findById(id).orElse(null);
  }

  @Transactional
  public ProductDTO addProduct(ProductDTO productDTO) {

    return this.productRepository.save(productDTO);
  }

  @Transactional
  public ProductDTO updateProduct(Long id, ProductDTO updatedProductDTO) {

    ProductDTO existingProductDTO = this.productRepository.findById(id).orElse(null);
    if (existingProductDTO != null) {
      existingProductDTO.setName(updatedProductDTO.getName());
      existingProductDTO.setDescription(updatedProductDTO.getDescription());
      existingProductDTO.setPrice(updatedProductDTO.getPrice());
      existingProductDTO.setAvailability(updatedProductDTO.getAvailability());
      existingProductDTO.setCategory(updatedProductDTO.getCategory());
      return this.productRepository.save(existingProductDTO);
    }
    return null;
  }

  @Transactional
  public ProductDTO deleteProduct(Long id) {

    ProductDTO deletedProductDTO = this.productRepository.findById(id).orElse(null);
    if (deletedProductDTO != null) {
      this.productRepository.deleteById(id);
    }
    return deletedProductDTO;
  }
}
