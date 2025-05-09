package com.example.DigitalParkingSystem.entity;

import com.example.DigitalParkingSystem.util.ParkingSpotStatus;
import com.example.DigitalParkingSystem.util.VehicleType;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSpot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private VehicleType type;
    private ParkingSpotStatus status;
    private int spotNumber;
    
    @ManyToOne
    private Floor floor;
    
    public boolean isOccupied() {
        return this.status == ParkingSpotStatus.OCCUPIED;
    }
    
    public void occupy() {
        this.status = ParkingSpotStatus.OCCUPIED;
    }
    
    public void free() {
        this.status = ParkingSpotStatus.FREE;
    }
}
