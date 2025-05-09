package com.example.DigitalParkingSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.DigitalParkingSystem.entity.ParkingSpot;
import com.example.DigitalParkingSystem.util.ParkingSpotStatus;
import com.example.DigitalParkingSystem.util.VehicleType;

import java.util.List;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {
//    List<ParkingSpot> findByStatus(ParkingSpotStatus status);
//    List<ParkingSpot> findByStatusAndType(ParkingSpotStatus status, VehicleType type);
//    List<ParkingSpot> findByFloorIdAndStatusAndType(Long floorId, ParkingSpotStatus status, VehicleType type);
}
