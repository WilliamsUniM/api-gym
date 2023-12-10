package com.williams.gym.repository;

import com.williams.gym.models.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
  Client findByDocument(long document);
}
