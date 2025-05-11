package com.example.DigitalParkingSystem.service;

import com.example.DigitalParkingSystem.dao.*;
import com.example.DigitalParkingSystem.entity.*;
import com.example.DigitalParkingSystem.responsestructure.ResponseStructure;
import com.example.DigitalParkingSystem.util.VehicleType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ParkingService {
    @Autowired
    private VehicleDao vehicleDao;
    
    @Autowired
    private ParkingLotDao parkingLotDao;
    
    @Autowired
    private FloorDao floorDao;
    
    @Autowired
    private ParkingSpotDao parkingSpotDao;
    
    @Autowired
    private TicketDao ticketDao;
    
    private Map<Long, Ticket> activeTickets = new HashMap<>();

    public ResponseEntity<?> createParkingLot(ParkingLot parkingLot) {
        Object pLot = parkingLotDao.createParkingLot(parkingLot);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseStructure
                .builder()
                .status(HttpStatus.CREATED.value())
                .message("ParkingLot saved successfully")
                .body(pLot)
                .build());
    }

    public ResponseEntity<?> getAllParkingLots() {
        List<ParkingLot> allParkingLots = parkingLotDao.getAllParkingLots();
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseStructure
                .builder()
                .status(HttpStatus.OK.value())
                .message("ParkingLot saved successfully")
                .body(allParkingLots)
                .build());
    }

    public ResponseEntity<?> getParkingLotById(Long id) {
        Optional<ParkingLot> parkingLot = parkingLotDao.getAllParkingLotById(id);
        
        if(parkingLot.isEmpty()) {
            throw new RuntimeException("Invalid ParkingLot Id, Unable to find");
        }
        
        return ResponseEntity.status(HttpStatus.FOUND).body(
                ResponseStructure
                .builder()
                .status(HttpStatus.FOUND.value())
                .message("ParkingLot found successfully!")
                .body(parkingLot)
                .build());
    }

    public ResponseEntity<?> addFloor(Floor floor) {
        
        Floor f= floorDao.addFloor(floor);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseStructure
                .builder()
                .status(HttpStatus.CREATED.value())
                .message("Floor added successfully")
                .body(f)
                .build());
    }

    public ResponseEntity<?> getAllFloors() {
        
        List<Floor> allFloors = floorDao.getAllFloors();
        
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseStructure
                .builder()
                .status(HttpStatus.OK.value())
                .message("Floor added successfully")
                .body(allFloors)
                .build());
    }

    public ResponseEntity<?> addParkingSpot(ParkingSpot parkingSpot) {
        
        ParkingSpot parkSpot = parkingSpotDao.addParkingSpot(parkingSpot);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseStructure
                .builder()
                .status(HttpStatus.CREATED.value())
                .message("Floor added successfully")
                .body(parkSpot)
                .build());
    }

    public ResponseEntity<?> registerVehicle(Vehicle vehicle) {
        
        Vehicle v = vehicleDao.registerVehicle(vehicle);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseStructure
                .builder()
                .status(HttpStatus.CREATED.value())
                .message("Vehicle registered successfully")
                .body(v)
                .build());
    }
    
    public ResponseEntity<?> getAllFreeSpots() {
        List<ParkingSpot> freeSpots = parkingSpotDao.getAllFreeSpots();
        
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseStructure
                .builder()
                .status(HttpStatus.OK.value())
                .message("Free parking spots retrieved successfully")
                .body(freeSpots)
                .build());
    }
    
    public ResponseEntity<?> getFreeSpotsByType(VehicleType type) {
        List<ParkingSpot> freeSpotsByType = parkingSpotDao.getFreeParkingSpotsByType(type);
        
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseStructure
                .builder()
                .status(HttpStatus.OK.value())
                .message("Free parking spots of type " + type + " retrieved successfully")
                .body(freeSpotsByType)
                .build());
    }
    
    public ResponseEntity<?> getVehicleByVehicleNumber(String vehicleNumber) {
        Optional<Vehicle> vehicle = vehicleDao.getVehicleByVehicleNumber(vehicleNumber);
        
        if(vehicle.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ResponseStructure
                    .builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message("Vehicle with number " + vehicleNumber + " not found")
                    .body(null)
                    .build());
        }
        
        return ResponseEntity.status(HttpStatus.FOUND).body(
                ResponseStructure
                .builder()
                .status(HttpStatus.FOUND.value())
                .message("Vehicle found successfully")
                .body(vehicle.get())
                .build());
    }
    
    @Transactional
    public ResponseEntity<?> parkVehicle(Vehicle vehicle) {
        // First check if the vehicle is already registered
        Optional<Vehicle> existingVehicle = vehicleDao.getVehicleByVehicleNumber(vehicle.getVehicleNumber());
        
        Vehicle vehicleToUse;
        if(existingVehicle.isPresent()) {
            vehicleToUse = existingVehicle.get();
        } else {
            vehicleToUse = vehicleDao.registerVehicle(vehicle);
        }
        
        // Find a free spot for this vehicle type
        List<ParkingSpot> freeSpots = parkingSpotDao.getFreeParkingSpotsByType(vehicleToUse.getType());
        
        if(freeSpots.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ResponseStructure
                    .builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message("No free parking spots available for vehicle type " + vehicleToUse.getType())
                    .body(null)
                    .build());
        }
        
        // Get the first available spot
        ParkingSpot spot = freeSpots.get(0);
        spot.occupy();
        parkingSpotDao.updateParkingSpot(spot);
        
        // Create a ticket
        Ticket ticket = new Ticket(vehicleToUse, spot);
        Ticket savedTicket = ticketDao.saveTicket(ticket);
        
        // Add to active tickets map
        activeTickets.put(savedTicket.getId(), savedTicket);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseStructure
                .builder()
                .status(HttpStatus.CREATED.value())
                .message("Vehicle parked successfully")
                .body(savedTicket)
                .build());
    }
    
    @Transactional
    public ResponseEntity<?> unparkVehicle(Long ticketId) {
        Optional<Ticket> ticketOpt = ticketDao.getTicketById(ticketId);
        
        if(ticketOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ResponseStructure
                    .builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message("Invalid ticket ID")
                    .body(null)
                    .build());
        }
        
        Ticket ticket = ticketOpt.get();
        
        if(ticket.getExitTime() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ResponseStructure
                    .builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message("Vehicle already exited")
                    .body(null)
                    .build());
        }
        
        // Calculate parking fee based on time spent
        ticket.setExitTime(LocalDateTime.now());
        
        // Free the parking spot
        ParkingSpot spot = ticket.getSpot();
        spot.free();
        parkingSpotDao.updateParkingSpot(spot);
        
        // Calculate amount based on time spent (hourly rate)
        long hoursParked = ChronoUnit.HOURS.between(ticket.getEntryTime(), ticket.getExitTime());
        if (hoursParked == 0) hoursParked = 1; // Minimum 1 hour charge
        
        double hourlyRate;
        switch(ticket.getVehicle().getType()) {
            case BIKE:
                hourlyRate = 10.0;
                break;
            case CAR:
                hourlyRate = 20.0;
                break;
            case TRUCK:
                hourlyRate = 30.0;
                break;
            default:
                hourlyRate = 20.0;
        }
        
        ticket.setAmount(hourlyRate * hoursParked);
        
        // Save updated ticket
        Ticket updatedTicket = ticketDao.saveTicket(ticket);
        
        // Remove from active tickets
        activeTickets.remove(ticketId);
        
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseStructure
                .builder()
                .status(HttpStatus.OK.value())
                .message("Vehicle unparked successfully")
                .body(updatedTicket)
                .build());
    }
    
    public ResponseEntity<?> getActiveTickets() {
        List<Ticket> tickets = ticketDao.getActiveTickets();
        
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseStructure
                .builder()
                .status(HttpStatus.OK.value())
                .message("Active tickets retrieved successfully")
                .body(tickets)
                .build());
    }
}