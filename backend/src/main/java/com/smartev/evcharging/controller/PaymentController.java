package com.smartev.evcharging.controller;

import com.smartev.evcharging.entity.Payment;
import com.smartev.evcharging.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin("*")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // Make Payment
    @PostMapping("/pay")
    public Object makePayment(@RequestBody Payment payment) {

        Payment result = paymentService.makePayment(payment);

        if (result == null) {
            return "Invalid booking or payment already completed for this booking";
        }

        return result;
    }

    // Get All Payments
    @GetMapping("/all")
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    // Get Payments By User
    @GetMapping("/user/{userId}")
    public List<Payment> getPaymentsByUser(
            @PathVariable Long userId) {

        return paymentService.getPaymentsByUser(userId);
    }

    // Get Payment By Booking
    @GetMapping("/booking/{bookingId}")
    public Payment getPaymentByBooking(
            @PathVariable Long bookingId) {

        return paymentService.getPaymentByBooking(bookingId);
    }

    // Get Payment By ID
    @GetMapping("/{id}")
    public Payment getPaymentById(
            @PathVariable Long id) {

        return paymentService.getPaymentById(id);
    }
}