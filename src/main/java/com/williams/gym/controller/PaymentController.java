package com.williams.gym.controller;

import com.williams.gym.models.request.PaymentRequest;
import com.williams.gym.models.request.TypePayment;
import com.williams.gym.models.response.PaymentResponse;
import com.williams.gym.services.PaymentService;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/payment")
public class PaymentController {

  private final PaymentService paymentService;

  @PostMapping
  public ResponseEntity<PaymentResponse> createPayment(@RequestBody PaymentRequest paymentRequest) {
    PaymentResponse response = paymentService.createPayment(paymentRequest);
    if (response.getId() == 0) {
      return ResponseEntity.badRequest().body(response);
    }
    return ResponseEntity.ok().body(response);
  }

  @GetMapping("/type")
  public ResponseEntity<List<TypePayment>> getTypes() {
    return ResponseEntity.ok().body(Arrays.asList(TypePayment.values()));
  }
}
