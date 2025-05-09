package com.example.DigitalParkingSystem.service;

import com.example.DigitalParkingSystem.dao.*;
import com.example.DigitalParkingSystem.entity.*;
import com.example.DigitalParkingSystem.responsestructure.ResponseStructure;
import com.example.DigitalParkingSystem.util.ParkingSpotStatus;
import com.example.DigitalParkingSystem.util.VehicleType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
}

	
    
    
//    public ResponseEntity<?> addFloor(ParkingLot parkingLot, int floorNumber) {
//        Floor floor = new Floor();
//        floor.setNumber(floorNumber);
//        floor.setParkingLot(parkingLot);
//        floor.setParkingSpots(new ArrayList<>());
//        
//        Floor savedFloor = floorDao.addFloor(floor);
//        
//        if (parkingLot.getFloors() == null) {
//            parkingLot.setFloors(new ArrayList<>());
//        }
//        parkingLot.getFloors().add(savedFloor);
//        parkingLotDao.saveParkingLot(parkingLot);
//        
//        return savedFloor;
//    }




//    public ParkingSpot addParkingSpot(Floor floor, VehicleType type, int spotNumber) {
//        ParkingSpot spot = new ParkingSpot();
//        spot.setType(type);
//        spot.setStatus(ParkingSpotStatus.FREE);
//        spot.setSpotNumber(spotNumber);
//        spot.setFloor(floor);
//        
//        ParkingSpot savedSpot = parkingSpotDao.save(spot);
//        
//        if (floor.getParkingSpots() == null) {
//            floor.setParkingSpots(new ArrayList<>());
//        }
//        floor.getParkingSpots().add(savedSpot);
//        floorDao.save(floor);
//        
//        return savedSpot;
//    }
//    
//    public List<ParkingSpot> getAllFreeSpots() {
//        return parkingSpotDao.findFreeSpots();
//    }
//    
//    public List<ParkingSpot> getFreeSpotsByType(VehicleType type) {
//        return parkingSpotDao.findFreeSpotsByType(type);
//    }
//    
//    public ParkingSpot getNearestParkingSpot(VehicleType type) {
//        List<ParkingSpot> freeSpots = parkingSpotDao.findFreeSpotsByType(type);
//        if (freeSpots.isEmpty()) {
//            return null;
//        }
//        return freeSpots.get(0);
//    }
//    
//    public Vehicle registerVehicle(String licensePlate, VehicleType type) {
//        Vehicle vehicle = new Vehicle();
//        vehicle.setLicensePlate(licensePlate);
//        vehicle.setType(type);
//        return vehicleDao.save(vehicle);
//    }
//    
//    @Transactional
//    public Ticket parkVehicle(Vehicle vehicle) {
//        Vehicle existingVehicle = vehicleDao.findByLicensePlate(vehicle.getLicensePlate());
//        if (existingVehicle == null) {
//            existingVehicle = vehicleDao.save(vehicle);
//        }
//        
//        ParkingSpot parkingSpot = getNearestParkingSpot(existingVehicle.getType());
//        if (parkingSpot == null) {
//            throw new RuntimeException("No free parking spot available for " + existingVehicle.getType());
//        }
//        
//        Ticket ticket = new Ticket();
//        ticket.setVehicle(existingVehicle);
//        ticket.setSpot(parkingSpot);
//        ticket.setEntryTime(LocalDateTime.now());
//        
//        parkingSpot.setStatus(ParkingSpotStatus.OCCUPIED);
//        parkingSpotDao.save(parkingSpot);
//        
//        Ticket savedTicket = ticketDao.save(ticket);
//        
//        activeTickets.put(savedTicket.getId(), savedTicket);
//        
//        return savedTicket;
//    }
//    
//    @Transactional
//    public Vehicle unparkVehicle(Long ticketId) {
//        Ticket ticket = activeTickets.get(ticketId);
//        
//        if (ticket == null) {
//            Optional<Ticket> optionalTicket = ticketDao.findById(ticketId);
//            if (!optionalTicket.isPresent()) {
//                throw new RuntimeException("Invalid ticket ID");
//            }
//            ticket = optionalTicket.get();
//        }
//        
//        if (ticket.getExitTime() != null) {
//            throw new RuntimeException("Vehicle already unparked");
//        }
//        
//        ParkingSpot spot = ticket.getSpot();
//        Vehicle vehicle = ticket.getVehicle();
//        
//        ticket.setExitTime(LocalDateTime.now());
//        ticketDao.save(ticket);
//        
//        spot.setStatus(ParkingSpotStatus.FREE);
//        parkingSpotDao.save(spot);
//        
//        activeTickets.remove(ticketId);
//        
//        return vehicle;
//    }
//    
//    public List<Ticket> getActiveTickets() {
//        return ticketDao.findActiveTickets();
//    }
//    
//    public Optional<ParkingLot> getParkingLotById(Long id) {
//        return parkingLotDao.findById(id);
//    }
//    
//    public List<ParkingLot> getAllParkingLots() {
//        return parkingLotDao.findAll();
//    }
//    
//    public List<Floor> getAllFloors() {
//        return floorDao.findAll();
//    }
//    
//    public Optional<Floor> getFloorById(Long id) {
//        return floorDao.findById(id);
//    }
//
//	public Vehicle getVehicleByLicensePlate(String licensePlate) {
//		return null;
//	}


