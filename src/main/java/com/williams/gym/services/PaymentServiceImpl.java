package com.williams.gym.services;

import com.williams.gym.models.entities.Client;
import com.williams.gym.models.entities.Payment;
import com.williams.gym.models.request.PaymentRequest;
import com.williams.gym.models.response.PaymentResponse;
import com.williams.gym.repository.ClientRepository;
import com.williams.gym.repository.PaymentRepository;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

  private final PaymentRepository paymentRepository;
  private final ClientRepository clientRepository;
  @Override
  public PaymentResponse createPayment(PaymentRequest paymentRequest) {
    Client client = clientRepository.findByDocument(paymentRequest.getDocument());
    if (client == null) {
      return PaymentResponse.builder().build();
    }
    Payment payment = Payment.builder()
        .client(client)
        .amount(paymentRequest.getAmount())
        .type(paymentRequest.getType().name())
        .startAt(LocalDate.now())
        .finishAt(LocalDate.now().plusMonths(paymentRequest.getType().getMonths()))
        .build();

    payment = paymentRepository.save(payment);

    return PaymentResponse.builder()
        .id(payment.getId())
        .build();
  }
}
