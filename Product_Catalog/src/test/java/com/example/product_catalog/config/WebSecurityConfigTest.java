package com.example.product_catalog.config;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import com.example.product_catalog.jwtutils.JwtAuthenticationEntryPoint;
import com.example.product_catalog.jwtutils.JwtFilter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.cache.NullUserCache;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.accept.ContentNegotiationStrategy;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = { WebSecurityConfig.class, AuthenticationConfiguration.class })
@ExtendWith(SpringExtension.class)
class WebSecurityConfigTest {
  @Autowired
  private ApplicationContext applicationContext;

  @MockBean
  private AuthenticationTrustResolver authenticationTrustResolver;

  @MockBean
  private ContentNegotiationStrategy contentNegotiationStrategy;

  @MockBean
  private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

  @MockBean
  private JwtFilter jwtFilter;

  @Autowired
  private ObjectPostProcessor<Object> objectPostProcessor;

  @MockBean
  private UserDetailsService userDetailsService;

  @Autowired
  private WebSecurityConfig webSecurityConfig;

  /**
   * Method under test: {@link WebSecurityConfig#passwordEncoder()}
   */
  @Test
  void testPasswordEncoder() {

    assertTrue(webSecurityConfig.passwordEncoder() instanceof BCryptPasswordEncoder);
  }

  /**
   * Method under test: {@link WebSecurityConfig#configure(AuthenticationManagerBuilder)}
   */
  @Test
  void testConfigure() throws Exception {

    AuthenticationManagerBuilder auth = new AuthenticationManagerBuilder(objectPostProcessor);
    webSecurityConfig.configure(auth);
    assertTrue(((DaoAuthenticationProvider) ((ProviderManager) auth.getOrBuild()).getProviders().get(0)).getUserCache() instanceof NullUserCache);
    assertTrue(((DaoAuthenticationProvider) ((ProviderManager) auth.getOrBuild()).getProviders().get(0)).isHideUserNotFoundExceptions());
    assertFalse(((DaoAuthenticationProvider) ((ProviderManager) auth.getOrBuild()).getProviders().get(0)).isForcePrincipalAsString());
  }

  /**
   * Method under test: {@link WebSecurityConfig#configure(AuthenticationManagerBuilder)}
   */



  @Test
  void testConfigure3() throws Exception {

    AuthenticationManagerBuilder auth = new AuthenticationManagerBuilder(objectPostProcessor);
    auth.authenticationEventPublisher(new DefaultAuthenticationEventPublisher(mock(ApplicationEventPublisher.class)));
    webSecurityConfig.configure(auth);
    assertTrue(((DaoAuthenticationProvider) ((ProviderManager) auth.getOrBuild()).getProviders().get(0)).getUserCache() instanceof NullUserCache);
    assertTrue(((DaoAuthenticationProvider) ((ProviderManager) auth.getOrBuild()).getProviders().get(0)).isHideUserNotFoundExceptions());
    assertFalse(((DaoAuthenticationProvider) ((ProviderManager) auth.getOrBuild()).getProviders().get(0)).isForcePrincipalAsString());
  }





}

