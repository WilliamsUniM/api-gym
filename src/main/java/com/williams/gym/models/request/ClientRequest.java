package com.williams.gym.models.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClientRequest {
  private String name;
  private String email;
  private long phone;
  private long document;
  private long site;
}
