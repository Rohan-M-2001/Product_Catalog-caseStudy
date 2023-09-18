package com.example.product_catalog.controller;

import static org.mockito.Mockito.when;

import com.example.product_catalog.model.ProductDTO;
import com.example.product_catalog.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = { ProductController.class })
@ExtendWith(SpringExtension.class)
class ProductControllerTest {
  @Autowired
  private ProductController productController;

  @MockBean
  private ProductService productService;

  /**
   * Method under test: {@link ProductController#getAllProducts()}
   */
  @Test
  void testGetAllProducts() throws Exception {

    when(productService.getAllProducts()).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products");
    MockMvcBuilders.standaloneSetup(productController).build().perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("[]"));
  }

  /**
   * Method under test: {@link ProductController#getAllProducts()}
   */
  @Test
  void testGetAllProducts2() throws Exception {

    when(productService.getAllProducts()).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products");
    requestBuilder.characterEncoding("Encoding");
    MockMvcBuilders.standaloneSetup(productController).build().perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("[]"));
  }

  /**
   * Method under test: {@link ProductController#getProductById(Long)}
   */
  @Test
  void testGetProductById() throws Exception {

    ProductDTO productDTO = new ProductDTO();
    productDTO.setAvailability(1);
    productDTO.setCategory("Category");
    productDTO.setDescription("The characteristics of someone or something");
    productDTO.setId(1L);
    productDTO.setName("Name");
    productDTO.setPrice(10.0d);
    when(productService.getProductById(Mockito.<Long> any())).thenReturn(productDTO);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/{id}", 1L);
    MockMvcBuilders.standaloneSetup(productController).build().perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json")).andExpect(MockMvcResultMatchers.content().string(
                "{\"id\":1,\"name\":\"Name\",\"description\":\"The characteristics of someone or something\",\"price\":10.0,"
                    + "\"availability\":1,\"category\":\"Category\"}"));
  }

  /**
   * Method under test: {@link ProductController#updateProduct(Long, ProductDTO)}
   */
  @Test
  void testUpdateProduct() throws Exception {

    ProductDTO productDTO = new ProductDTO();
    productDTO.setAvailability(1);
    productDTO.setCategory("Category");
    productDTO.setDescription("The characteristics of someone or something");
    productDTO.setId(1L);
    productDTO.setName("Name");
    productDTO.setPrice(10.0d);
    when(productService.updateProduct(Mockito.<Long> any(), Mockito.<ProductDTO> any())).thenReturn(productDTO);

    ProductDTO productDTO2 = new ProductDTO();
    productDTO2.setAvailability(1);
    productDTO2.setCategory("Category");
    productDTO2.setDescription("The characteristics of someone or something");
    productDTO2.setId(1L);
    productDTO2.setName("Name");
    productDTO2.setPrice(10.0d);
    String content = (new ObjectMapper()).writeValueAsString(productDTO2);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/products/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON).content(content);
    MockMvcBuilders.standaloneSetup(productController).build().perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json")).andExpect(MockMvcResultMatchers.content().string(
                "{\"id\":1,\"name\":\"Name\",\"description\":\"The characteristics of someone or something\",\"price\":10.0,"
                    + "\"availability\":1,\"category\":\"Category\"}"));
  }

  /**
   * Method under test: {@link ProductController#addProduct(ProductDTO)}
   */
  @Test
  void testAddProduct() throws Exception {

    when(productService.getAllProducts()).thenReturn(new ArrayList<>());

    ProductDTO productDTO = new ProductDTO();
    productDTO.setAvailability(1);
    productDTO.setCategory("Category");
    productDTO.setDescription("The characteristics of someone or something");
    productDTO.setId(1L);
    productDTO.setName("Name");
    productDTO.setPrice(10.0d);
    String content = (new ObjectMapper()).writeValueAsString(productDTO);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products")
        .contentType(MediaType.APPLICATION_JSON).content(content);
    MockMvcBuilders.standaloneSetup(productController).build().perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("[]"));
  }

  /**
   * Method under test: {@link ProductController#deleteProduct(Long)}
   */
  @Test
  void testDeleteProduct() throws Exception {

    ProductDTO productDTO = new ProductDTO();
    productDTO.setAvailability(1);
    productDTO.setCategory("Category");
    productDTO.setDescription("The characteristics of someone or something");
    productDTO.setId(1L);
    productDTO.setName("Name");
    productDTO.setPrice(10.0d);
    when(productService.deleteProduct(Mockito.<Long> any())).thenReturn(productDTO);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/products/{id}", 1L);
    MockMvcBuilders.standaloneSetup(productController).build().perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json")).andExpect(MockMvcResultMatchers.content().string(
                "{\"id\":1,\"name\":\"Name\",\"description\":\"The characteristics of someone or something\",\"price\":10.0,"
                    + "\"availability\":1,\"category\":\"Category\"}"));
  }
}

