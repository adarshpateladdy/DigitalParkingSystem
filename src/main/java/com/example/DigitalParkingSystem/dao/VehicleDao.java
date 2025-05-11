package com.example.DigitalParkingSystem.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.DigitalParkingSystem.entity.Vehicle;
import com.example.DigitalParkingSystem.repository.VehicleRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class VehicleDao {
    @Autowired
    private VehicleRepository vehicleRepo;

    public Vehicle registerVehicle(Vehicle vehicle) {
        return vehicleRepo.save(vehicle);
    }
    
    public Optional<Vehicle> getVehicleById(Long id) {
        return vehicleRepo.findById(id);
    }
    
    public Optional<Vehicle> getVehicleByVehicleNumber(String vehicleNumber) {
        return vehicleRepo.findByVehicleNumber(vehicleNumber);
    }
    
    public List<Vehicle> getAllVehicles() {
        return vehicleRepo.findAll();
    }
}