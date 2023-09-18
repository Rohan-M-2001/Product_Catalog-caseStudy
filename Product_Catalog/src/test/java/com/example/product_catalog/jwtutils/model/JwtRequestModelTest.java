package com.example.product_catalog.jwtutils.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class JwtRequestModelTest {
  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link JwtRequestModel#JwtRequestModel()}
   *   <li>{@link JwtRequestModel#setPassword(String)}
   *   <li>{@link JwtRequestModel#setUsername(String)}
   *   <li>{@link JwtRequestModel#getPassword()}
   *   <li>{@link JwtRequestModel#getUsername()}
   * </ul>
   */
  @Test
  void testConstructor() {

    JwtRequestModel actualJwtRequestModel = new JwtRequestModel();
    actualJwtRequestModel.setPassword("iloveyou");
    actualJwtRequestModel.setUsername("janedoe");
    assertEquals("iloveyou", actualJwtRequestModel.getPassword());
    assertEquals("janedoe", actualJwtRequestModel.getUsername());
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link JwtRequestModel#JwtRequestModel(String, String)}
   *   <li>{@link JwtRequestModel#setPassword(String)}
   *   <li>{@link JwtRequestModel#setUsername(String)}
   *   <li>{@link JwtRequestModel#getPassword()}
   *   <li>{@link JwtRequestModel#getUsername()}
   * </ul>
   */
  @Test
  void testConstructor2() {

    JwtRequestModel actualJwtRequestModel = new JwtRequestModel("janedoe", "iloveyou");
    actualJwtRequestModel.setPassword("iloveyou");
    actualJwtRequestModel.setUsername("janedoe");
    assertEquals("iloveyou", actualJwtRequestModel.getPassword());
    assertEquals("janedoe", actualJwtRequestModel.getUsername());
  }
}

