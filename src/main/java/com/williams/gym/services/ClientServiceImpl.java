package com.williams.gym.services;

import com.williams.gym.models.entities.Client;
import com.williams.gym.models.entities.Site;
import com.williams.gym.models.request.ClientRequest;
import com.williams.gym.models.response.ClientResponse;
import com.williams.gym.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

  private final ClientRepository clientRepository;
  @Override
  public ClientResponse createClient(ClientRequest clientRequest) {
    Site site = Site.builder()
        .code(clientRequest.getSite())
        .build();
    Client client = Client.builder()
        .name(clientRequest.getName())
        .document(clientRequest.getDocument())
        .email(clientRequest.getEmail())
        .phone(clientRequest.getPhone())
        .site(site)
        .build();

    client = clientRepository.save(client);

    return ClientResponse.builder()
        .id(client.getId())
        .build();
  }
}
