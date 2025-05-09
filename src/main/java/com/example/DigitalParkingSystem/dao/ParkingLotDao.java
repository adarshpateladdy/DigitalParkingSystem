package com.example.DigitalParkingSystem.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.DigitalParkingSystem.entity.ParkingLot;
import com.example.DigitalParkingSystem.repository.ParkingLotRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class ParkingLotDao {
    @Autowired
    private ParkingLotRepository parkingLotRepo;

	public Object createParkingLot(ParkingLot parkingLot) {
		return parkingLotRepo.save(parkingLot);
	}

	public List<ParkingLot> getAllParkingLots() {
		return parkingLotRepo.findAll();
	}

	public Optional<ParkingLot> getAllParkingLotById(Long id) {
		return parkingLotRepo.findById(id);
	}
    
}

