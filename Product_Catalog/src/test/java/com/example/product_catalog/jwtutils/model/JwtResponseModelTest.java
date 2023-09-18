package com.example.product_catalog.jwtutils.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class JwtResponseModelTest {
  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link JwtResponseModel#JwtResponseModel(String)}
   *   <li>{@link JwtResponseModel#getToken()}
   * </ul>
   */
  @Test
  void testConstructor() {

    assertEquals("ABC123", (new JwtResponseModel("ABC123")).getToken());
  }
}

