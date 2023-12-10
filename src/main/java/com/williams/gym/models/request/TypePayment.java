package com.williams.gym.models.request;

import lombok.Getter;

@Getter
public enum TypePayment {
  MENSUAL(1), TRIMESTRAL(3), SEMESTRAL(6), ANUAL(12);

  private int months;

  TypePayment(int months) {
    this.months = months;
  }
}
