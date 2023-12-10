package com.williams.gym.controller;

import com.williams.gym.models.request.ClientRequest;
import com.williams.gym.models.response.ClientResponse;
import com.williams.gym.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/clients")
public class ClientController {

  private final ClientService clientService;

  @PostMapping
  public ResponseEntity<ClientResponse> createClient(@RequestBody ClientRequest clientRequest) {
    return ResponseEntity.ok().body(clientService.createClient(clientRequest));
  }
}
