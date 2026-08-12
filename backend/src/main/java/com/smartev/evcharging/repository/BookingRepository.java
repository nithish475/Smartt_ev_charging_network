package com.smartev.evcharging.repository;

import com.smartev.evcharging.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    // Get all bookings of a particular user
    List<Booking> findByUserId(Long userId);

    // Get all bookings for a particular charging station
    List<Booking> findByStationId(Long stationId);

    // Check whether the selected station is already booked
    boolean existsByStationIdAndBookingDateAndBookingTime(
            Long stationId,
            String bookingDate,
            String bookingTime
    );
}
