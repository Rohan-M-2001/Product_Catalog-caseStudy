package com.example.product_catalog.service;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.product_catalog.model.ProductDTO;
import com.example.product_catalog.repo.ProductRepository;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = { ProductService.class })
@ExtendWith(SpringExtension.class)
class ProductServiceTest {
  @MockBean
  private ProductRepository productRepository;

  @Autowired
  private ProductService productService;

  /**
   * Method under test: {@link ProductService#getAllProducts()}
   */
  @Test
  void testGetAllProducts() {

    ArrayList<ProductDTO> productDTOList = new ArrayList<>();
    when(productRepository.findAll()).thenReturn(productDTOList);
    List<ProductDTO> actualAllProductDTOS = productService.getAllProducts();
    assertSame(productDTOList, actualAllProductDTOS);
    assertTrue(actualAllProductDTOS.isEmpty());
    verify(productRepository).findAll();
  }

  /**
   * Method under test: {@link ProductService#getProductById(Long)}
   */
  @Test
  void testGetProductById() {

    ProductDTO productDTO = new ProductDTO();
    productDTO.setAvailability(1);
    productDTO.setCategory("Category");
    productDTO.setDescription("The characteristics of someone or something");
    productDTO.setId(1L);
    productDTO.setName("Name");
    productDTO.setPrice(10.0d);
    Optional<ProductDTO> ofResult = Optional.of(productDTO);
    when(productRepository.findById(Mockito.<Long> any())).thenReturn(ofResult);
    assertSame(productDTO, productService.getProductById(1L));
    verify(productRepository).findById(Mockito.<Long> any());
  }

  /**
   * Method under test: {@link ProductService#addProduct(ProductDTO)}
   */
  @Test
  void testAddProduct() {

    ProductDTO productDTO = new ProductDTO();
    productDTO.setAvailability(1);
    productDTO.setCategory("Category");
    productDTO.setDescription("The characteristics of someone or something");
    productDTO.setId(1L);
    productDTO.setName("Name");
    productDTO.setPrice(10.0d);
    when(productRepository.save(Mockito.<ProductDTO> any())).thenReturn(productDTO);

    ProductDTO productDTO2 = new ProductDTO();
    productDTO2.setAvailability(1);
    productDTO2.setCategory("Category");
    productDTO2.setDescription("The characteristics of someone or something");
    productDTO2.setId(1L);
    productDTO2.setName("Name");
    productDTO2.setPrice(10.0d);
    assertSame(productDTO, productService.addProduct(productDTO2));
    verify(productRepository).save(Mockito.<ProductDTO> any());
  }

  /**
   * Method under test: {@link ProductService#updateProduct(Long, ProductDTO)}
   */
  @Test
  void testUpdateProduct() {

    ProductDTO productDTO = new ProductDTO();
    productDTO.setAvailability(1);
    productDTO.setCategory("Category");
    productDTO.setDescription("The characteristics of someone or something");
    productDTO.setId(1L);
    productDTO.setName("Name");
    productDTO.setPrice(10.0d);
    Optional<ProductDTO> ofResult = Optional.of(productDTO);

    ProductDTO productDTO2 = new ProductDTO();
    productDTO2.setAvailability(1);
    productDTO2.setCategory("Category");
    productDTO2.setDescription("The characteristics of someone or something");
    productDTO2.setId(1L);
    productDTO2.setName("Name");
    productDTO2.setPrice(10.0d);
    when(productRepository.save(Mockito.<ProductDTO> any())).thenReturn(productDTO2);
    when(productRepository.findById(Mockito.<Long> any())).thenReturn(ofResult);

    ProductDTO updatedProductDTO = new ProductDTO();
    updatedProductDTO.setAvailability(1);
    updatedProductDTO.setCategory("Category");
    updatedProductDTO.setDescription("The characteristics of someone or something");
    updatedProductDTO.setId(1L);
    updatedProductDTO.setName("Name");
    updatedProductDTO.setPrice(10.0d);
    assertSame(productDTO2, productService.updateProduct(1L, updatedProductDTO));
    verify(productRepository).save(Mockito.<ProductDTO> any());
    verify(productRepository).findById(Mockito.<Long> any());
  }



  /**
   * Method under test: {@link ProductService#updateProduct(Long, ProductDTO)}
   */
  @Test
  void testUpdateProduct3() {

    ProductDTO productDTO = new ProductDTO();
    productDTO.setAvailability(1);
    productDTO.setCategory("Category");
    productDTO.setDescription("The characteristics of someone or something");
    productDTO.setId(1L);
    productDTO.setName("Name");
    productDTO.setPrice(10.0d);
    when(productRepository.save(Mockito.<ProductDTO> any())).thenReturn(productDTO);
    when(productRepository.findById(Mockito.<Long> any())).thenReturn(Optional.empty());

    ProductDTO updatedProductDTO = new ProductDTO();
    updatedProductDTO.setAvailability(1);
    updatedProductDTO.setCategory("Category");
    updatedProductDTO.setDescription("The characteristics of someone or something");
    updatedProductDTO.setId(1L);
    updatedProductDTO.setName("Name");
    updatedProductDTO.setPrice(10.0d);
    assertNull(productService.updateProduct(1L, updatedProductDTO));
    verify(productRepository).findById(Mockito.<Long> any());
  }

  /**
   * Method under test: {@link ProductService#deleteProduct(Long)}
   */
  @Test
  void testDeleteProduct() {

    ProductDTO productDTO = new ProductDTO();
    productDTO.setAvailability(1);
    productDTO.setCategory("Category");
    productDTO.setDescription("The characteristics of someone or something");
    productDTO.setId(1L);
    productDTO.setName("Name");
    productDTO.setPrice(10.0d);
    Optional<ProductDTO> ofResult = Optional.of(productDTO);
    doNothing().when(productRepository).deleteById(Mockito.<Long> any());
    when(productRepository.findById(Mockito.<Long> any())).thenReturn(ofResult);
    assertSame(productDTO, productService.deleteProduct(1L));
    verify(productRepository).findById(Mockito.<Long> any());
    verify(productRepository).deleteById(Mockito.<Long> any());
  }


  /**
   * Method under test: {@link ProductService#deleteProduct(Long)}
   */
  @Test
  void testDeleteProduct3() {

    doNothing().when(productRepository).deleteById(Mockito.<Long> any());
    when(productRepository.findById(Mockito.<Long> any())).thenReturn(Optional.empty());
    assertNull(productService.deleteProduct(1L));
    verify(productRepository).findById(Mockito.<Long> any());
  }
}

