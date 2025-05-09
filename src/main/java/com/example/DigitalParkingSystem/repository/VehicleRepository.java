package com.example.DigitalParkingSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.DigitalParkingSystem.entity.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
//    Vehicle findByLicensePlate(String licensePlate);
}

