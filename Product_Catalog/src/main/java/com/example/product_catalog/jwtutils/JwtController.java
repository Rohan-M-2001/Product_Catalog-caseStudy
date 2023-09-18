package com.example.product_catalog.jwtutils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.product_catalog.jwtutils.model.JwtRequestModel;
import com.example.product_catalog.jwtutils.model.JwtResponseModel;

@RestController
@CrossOrigin
public class JwtController {
  @Autowired
  private JwtUserDetailsService userDetailsService;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private TokenManager tokenManager;

  @PostMapping("/login")
  public ResponseEntity<Object> createToken(@RequestBody JwtRequestModel request) throws Exception {

    try {
      this.authenticationManager
          .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
    } catch (DisabledException e) {
      throw new Exception("USER_DISABLED", e);
    } catch (BadCredentialsException e) {
      throw new Exception("INVALID_CREDENTIALS", e);
    }
    final UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
    final String jwtToken = this.tokenManager.generateJwtToken(userDetails);
    return ResponseEntity.ok(new JwtResponseModel(jwtToken));
  }
}