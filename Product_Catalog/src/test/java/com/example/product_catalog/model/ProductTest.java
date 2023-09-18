package com.example.product_catalog.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ProductTest {
  /**
   * Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link ProductDTO}
   *   <li>{@link ProductDTO#setAvailability(Integer)}
   *   <li>{@link ProductDTO#setCategory(String)}
   *   <li>{@link ProductDTO#setDescription(String)}
   *   <li>{@link ProductDTO#setId(Long)}
   *   <li>{@link ProductDTO#setName(String)}
   *   <li>{@link ProductDTO#setPrice(Double)}
   *   <li>{@link ProductDTO#getAvailability()}
   *   <li>{@link ProductDTO#getCategory()}
   *   <li>{@link ProductDTO#getDescription()}
   *   <li>{@link ProductDTO#getId()}
   *   <li>{@link ProductDTO#getName()}
   *   <li>{@link ProductDTO#getPrice()}
   * </ul>
   */
  @Test
  void testConstructor() {

    ProductDTO actualProductDTO = new ProductDTO();
    actualProductDTO.setAvailability(1);
    actualProductDTO.setCategory("Category");
    actualProductDTO.setDescription("The characteristics of someone or something");
    actualProductDTO.setId(1L);
    actualProductDTO.setName("Name");
    actualProductDTO.setPrice(10.0d);
    assertEquals(1, actualProductDTO.getAvailability().intValue());
    assertEquals("Category", actualProductDTO.getCategory());
    assertEquals("The characteristics of someone or something", actualProductDTO.getDescription());
    assertEquals(1L, actualProductDTO.getId().longValue());
    assertEquals("Name", actualProductDTO.getName());
    assertEquals(10.0d, actualProductDTO.getPrice().doubleValue());
  }
}

