package com.williams.gym.controller;

import com.williams.gym.config.auth.JwtUtil;
import com.williams.gym.models.entities.Site;
import com.williams.gym.models.entities.User;
import com.williams.gym.models.request.LoginRequest;
import com.williams.gym.models.response.ErrorResponse;
import com.williams.gym.models.response.LoginResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class LoginController {

  private final AuthenticationManager authenticationManager;
  private final JwtUtil jwtUtil;

  @GetMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
    try {
      Authentication authentication =
          authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
      Site site = new Site();
      site.setName(authentication.getAuthorities().toArray()[1].toString());
      User user = new User();
      user.setEmail(authentication.getName());
      user.setSite(site);
      LoginResponse loginResponse = LoginResponse.builder()
          .email(authentication.getName())
          .token(jwtUtil.createToken(user)).build();
      return ResponseEntity.ok(loginResponse);
    } catch (BadCredentialsException e) {
      ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), "Invalid username or password");
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    } catch (Exception e) {
      ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
  }
}
