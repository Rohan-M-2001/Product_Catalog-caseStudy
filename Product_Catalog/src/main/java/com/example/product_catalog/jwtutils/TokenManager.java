package com.example.product_catalog.jwtutils;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenManager implements Serializable {

  private static final long serialVersionUID = 7008375124389347049L;

  public static final long TOKEN_VALIDITY = 10L * 60 * 60;

  @Value("${secret}")
  private String jwtSecret;

  public String generateJwtToken(UserDetails userDetails) {
    Map<String, Object> claims = new HashMap<>();
    return Jwts.builder()
        .setClaims(claims)
        .setSubject(userDetails.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
        .signWith(SignatureAlgorithm.HS512, this.jwtSecret)
        .compact();
  }

  public Boolean validateJwtToken(String token, UserDetails userDetails) {
    String username = getUsernameFromToken(token);
    if (username == null) {
      return false; // Invalid token format
    }

    Claims claims;
    try {
      claims = Jwts.parser()
          .setSigningKey(this.jwtSecret)
          .parseClaimsJws(token)
          .getBody();
    } catch (Exception e) {
      return false; // Invalid token
    }

    Boolean isTokenExpired = claims.getExpiration().before(new Date());
    return (username.equals(userDetails.getUsername()) && !isTokenExpired);
  }

  public String getUsernameFromToken(String token) {
    try {
      final Claims claims = Jwts.parser()
          .setSigningKey(this.jwtSecret)
          .parseClaimsJws(token)
          .getBody();
      return claims.getSubject();
    } catch (Exception e) {
      // Handle invalid token format gracefully
      return null; // or an appropriate response
    }
  }
}