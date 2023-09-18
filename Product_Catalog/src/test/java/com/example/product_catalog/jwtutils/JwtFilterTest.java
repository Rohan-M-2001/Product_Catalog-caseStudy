package com.example.product_catalog.jwtutils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class JwtFilterTest {

  @Mock
  private JwtUserDetailsService userDetailsService;

  @Mock
  private TokenManager tokenManager;

  @InjectMocks
  private JwtFilter jwtFilter;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testDoFilterInternal_ValidToken() throws ServletException, IOException {
    // Arrange
    MockHttpServletRequest request = new MockHttpServletRequest();
    MockHttpServletResponse response = new MockHttpServletResponse();
    FilterChain filterChain = mock(FilterChain.class);

    String validToken = "valid-token";
    String username = "testuser";

    // Mock tokenHeader
    request.addHeader("Authorization", "Bearer " + validToken);

    // Mock getUsernameFromToken
    when(tokenManager.getUsernameFromToken(validToken)).thenReturn(username);

    // Mock loadUserByUsername
    UserDetails userDetails = User.builder()
        .username(username)
        .password("testpassword")
        .authorities("ROLE_USER")
        .build();
    when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);

    // Mock validateJwtToken
    when(tokenManager.validateJwtToken(validToken, userDetails)).thenReturn(true);

    // Act
    jwtFilter.doFilterInternal(request, response, filterChain);

    // Assert
    // Ensure that SecurityContextHolder has been set with authentication
    assertNotNull(SecurityContextHolder.getContext().getAuthentication());

    // Ensure that filterChain.doFilter has been called
    verify(filterChain, times(1)).doFilter(request, response);
  }

  @Test
  public void testDoFilterInternal_InvalidToken() throws ServletException, IOException {
    // Arrange
    MockHttpServletRequest request = new MockHttpServletRequest();
    MockHttpServletResponse response = new MockHttpServletResponse();
    FilterChain filterChain = mock(FilterChain.class);

    String invalidToken = "invalid-token";

    // Mock tokenHeader with an invalid token
    request.addHeader("Authorization", "Bearer " + invalidToken);

    // Mock getUsernameFromToken, which will throw an exception for an invalid token
    when(tokenManager.getUsernameFromToken(invalidToken)).thenThrow(IllegalArgumentException.class);

    // Act
    jwtFilter.doFilterInternal(request, response, filterChain);

    // Assert
    // Ensure that SecurityContextHolder is not set with authentication
    assertNull(SecurityContextHolder.getContext().getAuthentication());

    // Ensure that filterChain.doFilter has been called
    verify(filterChain, times(1)).doFilter(request, response);
  }

  // Add more test cases to cover other scenarios (e.g., missing token, expired token, etc.)
}


