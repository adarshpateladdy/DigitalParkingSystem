
package com.example.DigitalParkingSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.DigitalParkingSystem.entity.Floor;

@Repository
public interface FloorRepository extends JpaRepository<Floor, Long> {
}
