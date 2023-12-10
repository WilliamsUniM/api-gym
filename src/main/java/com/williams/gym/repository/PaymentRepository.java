package com.williams.gym.repository;

import com.williams.gym.models.entities.Payment;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

  @Query("SELECT p FROM Payment p WHERE p.startAt >= ?1 and p.client.site.code = ?2 order by p.startAt desc")
  List<Payment> findAfterOrEqualStartAtAndSite(LocalDate startAt, long siteCode);

  @Query("SELECT p FROM Payment p WHERE p.client.site.code = ?1 AND p.startAt BETWEEN ?2 AND ?3 ORDER BY p.id DESC")
  List<Payment> findBySiteAndStartAtBetween(long siteCode, LocalDate startAt, LocalDate endAt);
}
