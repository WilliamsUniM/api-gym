package com.williams.gym.config.auth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@PropertySource("classpath:application.properties")
public class JwtProperties {
  @Value("${config.auth.jwt.secret}")
  private String secret;
  @Value("${config.auth.jwt.expiration}")
  private long expiration;
}
