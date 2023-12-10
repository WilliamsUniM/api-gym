package com.williams.gym.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class SiteReportResponse {
  private int monthNumber;
  private String month;
  private long amount;
}
