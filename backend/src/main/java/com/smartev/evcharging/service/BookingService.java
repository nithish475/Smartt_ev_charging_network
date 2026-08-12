package com.smartev.evcharging.service;

import com.smartev.evcharging.entity.Booking;
import com.smartev.evcharging.entity.User;
import com.smartev.evcharging.repository.BookingRepository;
import com.smartev.evcharging.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;


    public Booking createBooking(
            Booking booking,
            String email) {

        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        booking.setUserId(user.getId());

        booking.setPaymentStatus("PENDING");

        return bookingRepository.save(booking);
    }


    // Logged-in user's bookings
    public List<Booking> getBookingsByEmail(
            String email) {

        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        return bookingRepository.findByUserId(
                user.getId()
        );
    }


    public List<Booking> getUserBookings(
            Long userId) {

        return bookingRepository.findByUserId(userId);
    }


    public List<Booking> getAllBookings() {

        return bookingRepository.findAll();
    }


    public long getBookingCount() {

        return bookingRepository.count();
    }
    public List<Booking> getMyBookings(String email) {

        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        return bookingRepository.findByUserId(user.getId());
    }

}