package com.example.DigitalParkingSystem.controller;

import com.example.DigitalParkingSystem.entity.*;
import com.example.DigitalParkingSystem.service.ParkingService;
import com.example.DigitalParkingSystem.util.VehicleType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/parking")
public class ParkingController {
	@Autowired
	private ParkingService service;

	
	// PARKING LOTS
	//--------------
	@PostMapping("/parkingLots")
	public ResponseEntity<?> createParkingLot(@RequestBody ParkingLot parkingLot){
		return service.createParkingLot(parkingLot);
	}

    @GetMapping("/parkingLots")
    public ResponseEntity<?> getAllParkingLots() {
        return service.getAllParkingLots();
    }
    
    @GetMapping("/parkingLots/{id}")
    public ResponseEntity<?> getParkingLotById(@PathVariable Long id){
    	return service.getParkingLotById(id);
    }
    
    // FLOORS
    //--------
    @PostMapping("/floors")
    public ResponseEntity<?> addFloor(@RequestBody Floor floor){
    	return service.addFloor(floor);
    }
    
    @GetMapping("/floors")
    public ResponseEntity<?> getAllFloors() {
        return service.getAllFloors();
    }
    
    // SPOTS
    //-------
    
    @PostMapping("/spots")
    public ResponseEntity<?> addParkingSpot(@RequestBody ParkingSpot parkingSpot){
    	return service.addParkingSpot(parkingSpot);
    }
    
//    @GetMapping("/spots/free")
//    public ResponseEntity<List<ParkingSpot>> getAllFreeSpots() {
//        return new ResponseEntity<>(service.getAllFreeSpots(), HttpStatus.OK);
//    }
//    
//    @GetMapping("/spots/free/{type}")
//    public ResponseEntity<List<ParkingSpot>> getFreeSpotsByType(@PathVariable VehicleType type) {
//        return new ResponseEntity<>(service.getFreeSpotsByType(type), HttpStatus.OK);
//    }
    
    // VEHICLES
    //----------
    
    @PostMapping("/vehicles")
    public ResponseEntity<?> registerVehicle(@RequestBody Vehicle vehicle) {
        return service.registerVehicle(vehicle);
    }
//    
//    @GetMapping("/vehicles/{licensePlate}")
//    public ResponseEntity<Vehicle> getVehicleByLicensePlate(@PathVariable String licensePlate) {
//        Vehicle vehicle = service.getVehicleByLicensePlate(licensePlate);
//        if (vehicle == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(vehicle, HttpStatus.OK);
//    }
//    
//    // Parking operations
//    @PostMapping("/park")
//    public ResponseEntity<Ticket> parkVehicle(@RequestBody Vehicle vehicle) {
//        try {
//            Ticket ticket = service.parkVehicle(vehicle);
//            return new ResponseEntity<>(ticket, HttpStatus.CREATED);
//        } catch (RuntimeException e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }
//    
//    @PostMapping("/unpark/{ticketId}")
//    public ResponseEntity<Vehicle> unparkVehicle(@PathVariable Long ticketId) {
//        try {
//            Vehicle vehicle = service.unparkVehicle(ticketId);
//            return new ResponseEntity<>(vehicle, HttpStatus.OK);
//        } catch (RuntimeException e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }
//    
//    @GetMapping("/tickets/active")
//    public ResponseEntity<List<Ticket>> getActiveTickets() {
//        return new ResponseEntity<>(service.getActiveTickets(), HttpStatus.OK);
//    }
}
