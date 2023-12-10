package com.williams.gym.config.auth;

import com.williams.gym.models.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
@Component
public class JwtUtil {

  private final JwtParser jwtParser;
  private final JwtProperties properties;

  private final String TOKEN_HEADER = "Authorization";
  private final String TOKEN_PREFIX = "Bearer ";

  public JwtUtil(JwtProperties properties) throws NoSuchAlgorithmException, InvalidKeySpecException {
    this.properties = properties;
    this.jwtParser = Jwts.parser().setSigningKey(getSigningKey()).build();
  }

  private Key getSigningKey() {
    byte[] keyBytes = Decoders.BASE64.decode(properties.getSecret());
    return Keys.hmacShaKeyFor(keyBytes);
  }

  public String createToken(User user) throws NoSuchAlgorithmException, InvalidKeySpecException {
    Claims claims = Jwts.claims().subject(user.getEmail()).add("site", user.getSite().getName()).build();
    LocalDateTime now = LocalDateTime.now();
    Date expiration = java.util.Date.from(now.plusHours(properties.getExpiration()).atZone(ZoneId.systemDefault()).toInstant());
    return Jwts.builder()
        .claims(claims)
        .expiration(expiration)
        .signWith(getSigningKey())
        .compact();
  }

  private Claims parseJwtClaims(String token) {
    return jwtParser.parseSignedClaims(token).getPayload();
  }


  public Claims resolveClaims(HttpServletRequest req) {
    try {
      String token = resolveToken(req);
      if (token != null) {
        return parseJwtClaims(token);
      }
      return null;
    } catch (ExpiredJwtException ex) {
      req.setAttribute("expired", ex.getMessage());
      throw ex;
    } catch (Exception ex) {
      req.setAttribute("invalid", ex.getMessage());
      throw ex;
    }
  }

  public String resolveToken(HttpServletRequest request) {

    String bearerToken = request.getHeader(TOKEN_HEADER);
    if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
      return bearerToken.substring(TOKEN_PREFIX.length());
    }
    return null;
  }

  public boolean validateClaims(Claims claims) throws AuthenticationException {
    try {
      return claims.getExpiration().after(new Date());
    } catch (Exception e) {
      throw e;
    }
  }

  public String getEmail(Claims claims) {
    return claims.getSubject();
  }

  public String getSite(Claims claims) {
    return claims.get("site", String.class);
  }
}
