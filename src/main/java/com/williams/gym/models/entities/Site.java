package com.williams.gym.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sites")
@NoArgsConstructor
public class Site {

  @Id
  @GeneratedValue
  private long code;

  @Column(name = "name", nullable = false, length = 100)
  private String name;

  @OneToOne
  @JoinColumn(name = "city", nullable = false)
  private City city;
}
