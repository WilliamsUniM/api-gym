package com.williams.gym.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cities")
@NoArgsConstructor
public class City {

  @Id
  @Column(name = "code", nullable = false)
  private int code;

  @Column(name = "name", length = 100, nullable = false)
  private String name;
}
