package com.example.DigitalParkingSystem.entity;

import com.example.DigitalParkingSystem.util.VehicleType;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    private Vehicle vehicle;
    
    @ManyToOne
    private ParkingSpot spot;
    
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private double amount;
    
    public Ticket(Vehicle vehicle, ParkingSpot spot) {
        this.vehicle = vehicle;
        this.spot = spot;
        this.entryTime = LocalDateTime.now();
        
        if (vehicle.getType() == VehicleType.BIKE) {
            this.amount = 10.0;
        } else if (vehicle.getType() == VehicleType.CAR) {
            this.amount = 20.0;
        } else {
            this.amount = 30.0;
        }
    }
}