package com.smartev.evcharging.controller;

import com.smartev.evcharging.entity.ChargingStation;
import com.smartev.evcharging.service.ChargingStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stations")
@CrossOrigin("*")
public class ChargingStationController {

    @Autowired
    private ChargingStationService chargingStationService;

    // Add station
    @PostMapping
    public ChargingStation addStation(
            @RequestBody ChargingStation station) {

        return chargingStationService.addStation(station);
    }

    // Get all stations
    @GetMapping
    public List<ChargingStation> getAllStations() {

        return chargingStationService.getAllStations();
    }

    // Get station by ID
    @GetMapping("/{id}")
    public ChargingStation getStationById(
            @PathVariable Long id) {

        return chargingStationService.getStationById(id);
    }

    // Update station
    @PutMapping("/{id}")
    public ChargingStation updateStation(
            @PathVariable Long id,
            @RequestBody ChargingStation station) {

        return chargingStationService
                .updateStation(id, station);
    }

    // Delete station
    @DeleteMapping("/{id}")
    public String deleteStation(
            @PathVariable Long id) {

        boolean deleted =
                chargingStationService
                        .deleteStation(id);

        if (deleted) {
            return "Station deleted successfully";
        }

        return "Station not found";
    }

    // Search station by location
    @GetMapping("/search")
    public List<ChargingStation> searchStations(
            @RequestParam String location) {

        return chargingStationService
                .searchByLocation(location);
    }

    // Available station count
    @GetMapping("/count/available")
    public long getAvailableStationCount() {

        return chargingStationService
                .getAvailableStationCount();
    }

    // Unavailable station count
    @GetMapping("/count/unavailable")
    public long getUnavailableStationCount() {

        return chargingStationService
                .getUnavailableStationCount();
    }
}