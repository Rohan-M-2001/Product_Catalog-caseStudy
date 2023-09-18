package com.example.product_catalog.jwtutils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = { JwtUserDetailsService.class })
@ExtendWith(SpringExtension.class)
class JwtUserDetailsServiceTest {
  @Autowired
  private JwtUserDetailsService jwtUserDetailsService;

  /**
   * Method under test: {@link JwtUserDetailsService#loadUserByUsername(String)}
   */
  @Test
  void testLoadUserByUsername() throws UsernameNotFoundException {

    assertThrows(UsernameNotFoundException.class, () -> jwtUserDetailsService.loadUserByUsername("janedoe"));
  }

  /**
   * Method under test: {@link JwtUserDetailsService#loadUserByUsername(String)}
   */
  @Test
  void testLoadUserByUsername2() throws UsernameNotFoundException {

    UserDetails actualLoadUserByUsernameResult = jwtUserDetailsService.loadUserByUsername("randomuser123");
    assertTrue(actualLoadUserByUsernameResult.getAuthorities().isEmpty());
    assertTrue(actualLoadUserByUsernameResult.isEnabled());
    assertTrue(actualLoadUserByUsernameResult.isCredentialsNonExpired());
    assertTrue(actualLoadUserByUsernameResult.isAccountNonLocked());
    assertTrue(actualLoadUserByUsernameResult.isAccountNonExpired());
    assertEquals("randomuser123", actualLoadUserByUsernameResult.getUsername());
    assertEquals("$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
        actualLoadUserByUsernameResult.getPassword());
  }
}

