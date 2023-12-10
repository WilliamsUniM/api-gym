package com.williams.gym.models.response;

import com.williams.gym.models.request.TypePayment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ReportResponse {
  private long idPayment;
  private long document;
  private String name;
  private TypePayment type;
  private long amount;
}
