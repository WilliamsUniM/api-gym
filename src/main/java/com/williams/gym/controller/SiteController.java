package com.williams.gym.controller;

import com.williams.gym.models.response.SiteReportResponse;
import com.williams.gym.models.response.SiteResponse;
import com.williams.gym.services.SiteService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/sites")
public class SiteController {

  private final SiteService siteService;
  @GetMapping
  public ResponseEntity<List<SiteResponse>> getHome() {
    return ResponseEntity.ok(siteService.getAllSites());
  }

  @GetMapping("/payments")
  public ResponseEntity<List<SiteReportResponse>> getPayments(@RequestParam long siteCode) {
    return ResponseEntity.ok(siteService.getPayments(siteCode));
  }
}
