package com.williams.gym.services;

import com.williams.gym.models.entities.Payment;
import com.williams.gym.models.request.TypePayment;
import com.williams.gym.models.response.ReportResponse;
import com.williams.gym.repository.PaymentRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReportServiceImpl implements ReportService {

  private final PaymentRepository paymentRepository;

  @Override
  public List<ReportResponse> getReportBySite(long idSite, LocalDate startDate, LocalDate endDate) {
    List<Payment> paymentList = paymentRepository.findBySiteAndStartAtBetween(idSite, startDate, endDate);
    return paymentList.stream().map(payment ->
      ReportResponse.builder()
          .amount(payment.getAmount())
          .idPayment(payment.getId())
          .type(TypePayment.valueOf(payment.getType()))
          .name(payment.getClient().getName())
          .document(payment.getClient().getDocument()).build()
    ).collect(Collectors.toList());
  }
}
