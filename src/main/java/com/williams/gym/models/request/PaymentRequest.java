package com.williams.gym.models.request;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PaymentRequest {
  private long document;
  private long amount;
  private TypePayment type;
}
