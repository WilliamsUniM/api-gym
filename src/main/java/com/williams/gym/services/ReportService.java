package com.williams.gym.services;

import com.williams.gym.models.response.ReportResponse;
import java.time.LocalDate;
import java.util.List;

public interface ReportService {

  List<ReportResponse> getReportBySite(long idSite, LocalDate startDate, LocalDate endDate);
}
