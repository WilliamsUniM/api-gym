package com.williams.gym.services;

import com.williams.gym.models.request.PaymentRequest;
import com.williams.gym.models.response.PaymentResponse;

public interface PaymentService {
  PaymentResponse createPayment(PaymentRequest paymentRequest);
}
