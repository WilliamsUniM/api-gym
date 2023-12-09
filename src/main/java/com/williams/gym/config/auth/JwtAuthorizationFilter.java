package com.williams.gym.config.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@AllArgsConstructor
@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {
  private final JwtUtil jwtUtil;
  private final ObjectMapper objectMapper;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    Map<String, Object> errorDetails = new HashMap<>();

    try {
      String accessToken = jwtUtil.resolveToken(request);

      if (accessToken == null ) {
        throw new ServletException("Invalid Token");
      }

      log.info("token : {}", accessToken);
      Claims claims = jwtUtil.resolveClaims(request);

      if( claims != null && jwtUtil.validateClaims(claims)){
        String email = claims.getSubject();
        log.info("email : {}", email);
        Authentication authentication =
            new UsernamePasswordAuthenticationToken(email,"",new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }

    } catch (Exception e){
      errorDetails.put("message", "Authentication Error");
      errorDetails.put("details",e.getMessage());
      response.setStatus(HttpStatus.FORBIDDEN.value());
      response.setContentType(MediaType.APPLICATION_JSON_VALUE);

      objectMapper.writeValue(response.getWriter(), errorDetails);

    }
    filterChain.doFilter(request, response);
  }
}
