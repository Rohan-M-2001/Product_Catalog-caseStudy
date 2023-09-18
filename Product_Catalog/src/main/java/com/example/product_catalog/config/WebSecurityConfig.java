package com.example.product_catalog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.product_catalog.jwtutils.JwtAuthenticationEntryPoint;
import com.example.product_catalog.jwtutils.JwtFilter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  private JwtAuthenticationEntryPoint authenticationEntryPoint;

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private JwtFilter filter;

  @Bean
  public PasswordEncoder passwordEncoder() {

    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    auth.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {

    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.csrf().disable().authorizeRequests().antMatchers("/login").permitAll().anyRequest().authenticated().and()
        .exceptionHandling().authenticationEntryPoint(this.authenticationEntryPoint).and().sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    http.addFilterBefore(this.filter, UsernamePasswordAuthenticationFilter.class);
  }
}
