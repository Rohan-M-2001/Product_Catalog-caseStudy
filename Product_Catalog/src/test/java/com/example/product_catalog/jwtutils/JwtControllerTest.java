package com.example.product_catalog.jwtutils;

import static org.mockito.Mockito.when;

import com.example.product_catalog.jwtutils.model.JwtRequestModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = { JwtController.class })
@ExtendWith(SpringExtension.class)
class JwtControllerTest {
  @MockBean
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtController jwtController;

  @MockBean
  private JwtUserDetailsService jwtUserDetailsService;

  @MockBean
  private TokenManager tokenManager;

  /**
   * Method under test: {@link JwtController#createToken(JwtRequestModel)}
   */
  @Test
  void testCreateToken() throws Exception {

    when(tokenManager.generateJwtToken(Mockito.<UserDetails> any())).thenReturn("ABC123");
    when(jwtUserDetailsService.loadUserByUsername(Mockito.<String> any())).thenReturn(
        new User("jondoe", "password", new ArrayList<>()));
    when(authenticationManager.authenticate(Mockito.<Authentication> any())).thenReturn(
        new TestingAuthenticationToken("Principal", "Credentials"));

    JwtRequestModel jwtRequestModel = new JwtRequestModel();
    jwtRequestModel.setPassword("password");
    jwtRequestModel.setUsername("jondoe");
    String content = (new ObjectMapper()).writeValueAsString(jwtRequestModel);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/login")
        .contentType(MediaType.APPLICATION_JSON).content(content);
    MockMvcBuilders.standaloneSetup(jwtController).build().perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("{\"token\":\"ABC123\"}"));
  }
}

