package com.williams.gym.services;

import com.williams.gym.models.response.SiteReportResponse;
import com.williams.gym.models.response.SiteResponse;
import java.util.List;

public interface SiteService {
  List<SiteResponse> getAllSites();

  List<SiteReportResponse> getPayments(long siteCode);
}
