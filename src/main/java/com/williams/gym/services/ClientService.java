package com.williams.gym.services;

import com.williams.gym.models.request.ClientRequest;
import com.williams.gym.models.response.ClientResponse;

public interface ClientService {
  ClientResponse createClient(ClientRequest clientRequest);
}
