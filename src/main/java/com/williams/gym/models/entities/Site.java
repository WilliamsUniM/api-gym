package com.williams.gym.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@Table(name = "sites")
@NoArgsConstructor
@AllArgsConstructor
public class Site {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private long code;

  @Column(name = "name", nullable = false, length = 100)
  private String name;

  @OneToOne
  @JoinColumn(name = "city", nullable = false)
  private City city;
}
