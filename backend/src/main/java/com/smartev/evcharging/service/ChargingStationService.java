package com.smartev.evcharging.service;

import com.smartev.evcharging.entity.ChargingStation;
import com.smartev.evcharging.repository.ChargingStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChargingStationService {

    @Autowired
    private ChargingStationRepository chargingStationRepository;

    // Add station
    public ChargingStation addStation(ChargingStation station) {

        return chargingStationRepository.save(station);
    }

    // Get all stations
    public List<ChargingStation> getAllStations() {

        return chargingStationRepository.findAll();
    }

    // Get station by ID
    public ChargingStation getStationById(Long id) {

        return chargingStationRepository
                .findById(id)
                .orElse(null);
    }

    // Update station
    public ChargingStation updateStation(
            Long id,
            ChargingStation updatedStation) {

        ChargingStation station =
                chargingStationRepository
                        .findById(id)
                        .orElse(null);

        if (station == null) {
            return null;
        }

        station.setStationName(
                updatedStation.getStationName()
        );

        station.setLocation(
                updatedStation.getLocation()
        );

        station.setChargerType(
                updatedStation.getChargerType()
        );

        station.setTotalPorts(
                updatedStation.getTotalPorts()
        );

        station.setPricePerHour(
                updatedStation.getPricePerHour()
        );

        station.setAvailable(
                updatedStation.isAvailable()
        );

        return chargingStationRepository.save(station);
    }

    // Delete station
    public boolean deleteStation(Long id) {

        if (!chargingStationRepository.existsById(id)) {
            return false;
        }

        chargingStationRepository.deleteById(id);

        return true;
    }

    // Search by location
    public List<ChargingStation> searchByLocation(
            String location) {

        return chargingStationRepository
                .findByLocationContainingIgnoreCase(location);
    }

    // Count available stations
    public long getAvailableStationCount() {

        return chargingStationRepository
                .countByAvailableTrue();
    }

    // Count unavailable stations
    public long getUnavailableStationCount() {

        return chargingStationRepository
                .countByAvailableFalse();
    }
    // Total number of charging stations
    public long getStationCount() {

        return chargingStationRepository.count();
    }

    // Number of occupied/unavailable stations
    public long getOccupiedStationCount() {

        return chargingStationRepository.countByAvailableFalse();
    }

    //hello world
}