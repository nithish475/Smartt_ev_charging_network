package com.smartev.evcharging.controller;

import com.smartev.evcharging.dto.UserResponse;
import com.smartev.evcharging.entity.Booking;
import com.smartev.evcharging.entity.ChargingStation;
import com.smartev.evcharging.service.BookingService;
import com.smartev.evcharging.service.ChargingStationService;
import com.smartev.evcharging.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private ChargingStationService chargingStationService;

    // View All Users
    @GetMapping("/users")
    public List<UserResponse> getUsers() {

        return userService.getAllUsers()
                .stream()
                .map(user -> new UserResponse(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getRole()
                ))
                .toList();
    }

    // View All Bookings
    @GetMapping("/bookings")
    public List<Booking> getBookings() {
        return bookingService.getAllBookings();
    }

    // View All Charging Stations
    @GetMapping("/stations")
    public List<ChargingStation> getStations() {
        return chargingStationService.getAllStations();
    }

    // Total Users
    @GetMapping("/users/count")
    public long getUserCount() {
        return userService.getUserCount();
    }

    // Total Stations
    @GetMapping("/stations/count")
    public long getStationCount() {
        return chargingStationService.getStationCount();
    }

    // Total Bookings
    @GetMapping("/bookings/count")
    public long getBookingCount() {
        return bookingService.getBookingCount();
    }

    // Available Stations Count
    @GetMapping("/stations/available")
    public long getAvailableStations() {
        return chargingStationService.getAvailableStationCount();
    }

    // Occupied Stations Count
    @GetMapping("/stations/occupied")
    public long getOccupiedStations() {
        return chargingStationService.getOccupiedStationCount();
    }
}