package com.smartev.evcharging.service;

import com.smartev.evcharging.entity.Booking;
import com.smartev.evcharging.entity.Payment;
import com.smartev.evcharging.repository.BookingRepository;
import com.smartev.evcharging.repository.PaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BookingRepository bookingRepository;

    public Payment makePayment(Payment payment) {

        Booking booking = bookingRepository
                .findById(payment.getBookingId())
                .orElse(null);

        if (booking == null) {
            return null;
        }

        Payment existingPayment =
                paymentRepository.findByBookingId(payment.getBookingId());

        if (existingPayment != null) {
            return null;
        }

        payment.setPaymentStatus("SUCCESS");

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        payment.setPaymentDate(
                LocalDateTime.now().format(formatter)
        );

        booking.setPaymentStatus("PAID");

        bookingRepository.save(booking);

        return paymentRepository.save(payment);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public List<Payment> getPaymentsByUser(Long userId) {
        return paymentRepository.findByUserId(userId);
    }

    public Payment getPaymentByBooking(Long bookingId) {
        return paymentRepository.findByBookingId(bookingId);
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }
}