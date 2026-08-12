package com.smartev.evcharging.repository;

import com.smartev.evcharging.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByUserId(Long userId);

    Payment findByBookingId(Long bookingId);
}