package com.williams.gym.controller;

import com.williams.gym.models.response.ReportResponse;
import com.williams.gym.services.ReportService;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/report")
public class ReportController {

  private final ReportService reportService;

  @GetMapping
  public ResponseEntity<List<ReportResponse>> getReport(@RequestParam long siteCode,
                                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
    return ResponseEntity.ok().body(reportService.getReportBySite(siteCode, startDate, endDate));
  }
}
