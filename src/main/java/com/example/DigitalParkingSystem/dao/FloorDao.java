package com.example.DigitalParkingSystem.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.DigitalParkingSystem.entity.Floor;
import com.example.DigitalParkingSystem.repository.FloorRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class FloorDao {
    @Autowired
    private FloorRepository floorRepo;

	public Floor addFloor(Floor floor) {
		return floorRepo.save(floor);
	}

	public List<Floor> getAllFloors() {
		return floorRepo.findAll();
	}
    
}

