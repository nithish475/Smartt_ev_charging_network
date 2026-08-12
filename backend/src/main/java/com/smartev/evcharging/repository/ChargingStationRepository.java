package com.smartev.evcharging.repository;

import com.smartev.evcharging.entity.ChargingStation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChargingStationRepository
        extends JpaRepository<ChargingStation, Long> {

    List<ChargingStation> findByLocationContainingIgnoreCase(String location);

    long countByAvailableTrue();

    long countByAvailableFalse();
}