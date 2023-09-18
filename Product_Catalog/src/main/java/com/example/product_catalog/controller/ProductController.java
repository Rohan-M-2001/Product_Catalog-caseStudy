package com.example.product_catalog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.product_catalog.model.ProductDTO;
import com.example.product_catalog.service.ProductService;

@RestController
@RequestMapping("/products")

public class ProductController {
  @Autowired
  private ProductService productService;

  @GetMapping
  public List<ProductDTO> getAllProducts() {

    return this.productService.getAllProducts();
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {

    ProductDTO product = this.productService.getProductById(id);
    if (product != null) {
      return ResponseEntity.ok(product);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO) {

    ProductDTO addedProductDTO = this.productService.addProduct(productDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(addedProductDTO);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO updatedProductDTO) {

    ProductDTO updated = this.productService.updateProduct(id, updatedProductDTO);
    if (updated != null) {
      return ResponseEntity.ok(updated);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ProductDTO> deleteProduct(@PathVariable Long id) {

    ProductDTO deletedProductDTO = this.productService.deleteProduct(id);
    if (deletedProductDTO != null) {
      return ResponseEntity.ok(deletedProductDTO);
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
