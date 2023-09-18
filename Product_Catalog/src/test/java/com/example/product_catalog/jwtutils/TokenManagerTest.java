package com.example.product_catalog.jwtutils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = { TokenManager.class })
@ExtendWith(SpringExtension.class)
class TokenManagerTest {

  @Autowired
  private TokenManager tokenManager;

  private UserDetails userDetails;

  @BeforeEach
  public void setUp() {
    userDetails = User.builder()
        .username("testuser")
        .password("testpassword")
        .authorities("ROLE_USER")
        .build();
  }

  @Test
  void testGenerateJwtToken() {
    // Act
    String jwtToken = tokenManager.generateJwtToken(userDetails);

    // Assert
    assertNotNull(jwtToken);

    // Decode the token to check its claims (you can validate other claims as needed)
    String username = tokenManager.getUsernameFromToken(jwtToken);
    assertEquals(userDetails.getUsername(), username);
  }

  @Test
  void testValidateJwtToken() {
    // Arrange
    String jwtToken = tokenManager.generateJwtToken(userDetails);

    // Act
    Boolean isValid = tokenManager.validateJwtToken(jwtToken, userDetails);

    // Assert
    assertTrue(isValid);
  }

  @Test
  void testValidateInvalidJwtToken() {
    // Arrange
    String jwtToken = "InvalidToken";

    // Act
    Boolean isValid = tokenManager.validateJwtToken(jwtToken, userDetails);

    // Assert
    assertFalse(isValid);
  }

  @Test
  void testGetUsernameFromToken() {
    // Arrange
    String jwtToken = tokenManager.generateJwtToken(userDetails);

    // Act
    String username = tokenManager.getUsernameFromToken(jwtToken);

    // Assert
    assertEquals(userDetails.getUsername(), username);
  }

  @Test
  void testGetUsernameFromInvalidToken() {
    // Act
    String username = tokenManager.getUsernameFromToken("InvalidToken");

    // Assert
    assertNull(username);
  }
}
