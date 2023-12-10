package com.williams.gym.services;

import com.williams.gym.models.entities.Payment;
import com.williams.gym.models.entities.Site;
import com.williams.gym.models.response.SiteReportResponse;
import com.williams.gym.models.response.SiteResponse;
import com.williams.gym.repository.PaymentRepository;
import com.williams.gym.repository.SiteRepository;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SiteServiceImpl implements SiteService{

  private final SiteRepository repository;
  private final PaymentRepository paymentRepository;
  @Override
  public List<SiteResponse> getAllSites() {
    List<Site> sites = repository.findAll();
    return sites.stream().map(site -> SiteResponse.builder()
        .code(site.getCode())
        .name(site.getName())
        .build()).toList();
  }

  @Override
  public List<SiteReportResponse> getPayments(long siteCode) {
    LocalDate startAt = LocalDate.now().minusMonths(6);
    List<Payment> paymentList = paymentRepository.findAfterOrEqualStartAtAndSite(startAt, siteCode);
    Map<String, Long> monthlyPayments = new HashMap<>();
    monthlyPayments.put(getKeyMonth(startAt), 0L);
    monthlyPayments.put(getKeyMonth(startAt.plusMonths(1)), 0L);
    monthlyPayments.put(getKeyMonth(startAt.plusMonths(2)), 0L);
    monthlyPayments.put(getKeyMonth(startAt.plusMonths(3)), 0L);
    monthlyPayments.put(getKeyMonth(startAt.plusMonths(4)), 0L);
    monthlyPayments.put(getKeyMonth(startAt.plusMonths(5)), 0L);
    paymentList.forEach(payment -> {
      String month = getKeyMonth(payment.getStartAt());
      if (monthlyPayments.containsKey(month)) {
        monthlyPayments.put(month, monthlyPayments.get(month) + payment.getAmount());
      } else {
        monthlyPayments.put(month, payment.getAmount());
      }
    });
    List<SiteReportResponse> listReturn = new java.util.ArrayList<>(monthlyPayments.entrySet().stream().map(entry -> SiteReportResponse.builder()
        .monthNumber(Integer.parseInt(entry.getKey().split("-")[0]))
        .month(entry.getKey().split("-")[1].substring(0, 3))
        .amount(entry.getValue())
        .build()).toList());
    listReturn.sort(Comparator.comparingInt(SiteReportResponse::getMonthNumber));
    return listReturn;
  }

  private String getKeyMonth(LocalDate date) {
    return String.format("%02d", date.getMonth().getValue()) + "-" + date.getMonth();
  }
}
