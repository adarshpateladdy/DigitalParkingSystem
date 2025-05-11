package com.example.DigitalParkingSystem.controller;

import com.example.DigitalParkingSystem.entity.*;
import com.example.DigitalParkingSystem.service.ParkingService;
import com.example.DigitalParkingSystem.util.VehicleType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parking")
public class ParkingController {
    @Autowired
    private ParkingService service;

    // PARKING LOTS
    // --------------
    @PostMapping("/parkingLots")
    public ResponseEntity<?> createParkingLot(@RequestBody ParkingLot parkingLot) {
        return service.createParkingLot(parkingLot);
    }

    @GetMapping("/parkingLots")
    public ResponseEntity<?> getAllParkingLots() {
        return service.getAllParkingLots();
    }

    @GetMapping("/parkingLots/{id}")
    public ResponseEntity<?> getParkingLotById(@PathVariable Long id) {
        return service.getParkingLotById(id);
    }

    // FLOORS
    // --------
    @PostMapping("/floors")
    public ResponseEntity<?> addFloor(@RequestBody Floor floor) {
        return service.addFloor(floor);
    }

    @GetMapping("/floors")
    public ResponseEntity<?> getAllFloors() {
        return service.getAllFloors();
    }

    // SPOTS
    // -------

    @PostMapping("/spots")
    public ResponseEntity<?> addParkingSpot(@RequestBody ParkingSpot parkingSpot) {
        return service.addParkingSpot(parkingSpot);
    }

    @GetMapping("/spots/free")
    public ResponseEntity<?> getAllFreeSpots() {
        return service.getAllFreeSpots();
    }

    @GetMapping("/spots/free/{type}")
    public ResponseEntity<?> getFreeSpotsByType(@PathVariable VehicleType type) {
        return service.getFreeSpotsByType(type);
    }

    // VEHICLES
    // ----------

    @PostMapping("/vehicles")
    public ResponseEntity<?> registerVehicle(@RequestBody Vehicle vehicle) {
        return service.registerVehicle(vehicle);
    }

    @GetMapping("/vehicles/{vehicleNumber}")
    public ResponseEntity<?> getVehicleByVehicleNumber(@PathVariable String vehicleNumber) {
        return service.getVehicleByVehicleNumber(vehicleNumber);
    }

    // Parking operations

    @PostMapping("/park")
    public ResponseEntity<?> parkVehicle(@RequestBody Vehicle vehicle) {
        return service.parkVehicle(vehicle);
    }


    @PostMapping("/unpark/{ticketId}")
    public ResponseEntity<?> unparkVehicle(@PathVariable Long ticketId) {
        return service.unparkVehicle(ticketId);
    }
    
    @GetMapping("/tickets/active")
    public ResponseEntity<?> getActiveTickets() {
        return service.getActiveTickets();
    }
}