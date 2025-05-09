package com.example.DigitalParkingSystem.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.DigitalParkingSystem.entity.ParkingSpot;
import com.example.DigitalParkingSystem.repository.ParkingSpotRepository;
import com.example.DigitalParkingSystem.util.ParkingSpotStatus;
import com.example.DigitalParkingSystem.util.VehicleType;

import java.util.List;
import java.util.Optional;

@Repository
public class ParkingSpotDao {
    @Autowired
    private ParkingSpotRepository parkingSpotRepo;

	public ParkingSpot addParkingSpot(ParkingSpot parkingSpot) {
		return parkingSpotRepo.save(parkingSpot);
	}
}
