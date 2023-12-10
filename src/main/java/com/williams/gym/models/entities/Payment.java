package com.williams.gym.models.entities;

import java.time.LocalDate;
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
@Table(name = "payments")
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private long id;

  @Column(name = "amount", nullable = false)
  private long amount;

  @Column(name = "start_at", nullable = false)
  private LocalDate startAt;

  @Column(name = "finish_at", nullable = false)
  private LocalDate finishAt;

  @Column(name = "type", nullable = false, length = 10)
  private String type;

  @OneToOne
  @JoinColumn(name = "client", nullable = false)
  private Client client;

}
