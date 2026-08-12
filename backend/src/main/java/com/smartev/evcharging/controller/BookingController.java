package com.smartev.evcharging.controller;

import com.smartev.evcharging.entity.Booking;
import com.smartev.evcharging.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin("*")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/book")
    public Booking createBooking(
            @RequestBody Booking booking,
            Authentication authentication) {

        String email = authentication.getName();

        return bookingService.createBooking(
                booking,
                email
        );
    }

    // ⭐ NEW - logged-in user's bookings
    @GetMapping("/my")
    public List<Booking> getMyBookings(
            Authentication authentication) {

        String email = authentication.getName();

        return bookingService.getMyBookings(email);
    }

    @GetMapping("/user/{userId}")
    public List<Booking> getUserBookings(
            @PathVariable Long userId) {

        return bookingService.getUserBookings(userId);
    }

    @GetMapping("/all")
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }
}