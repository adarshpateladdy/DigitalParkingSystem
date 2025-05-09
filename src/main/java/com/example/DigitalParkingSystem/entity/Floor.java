package com.example.DigitalParkingSystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Floor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int number;
    
    @OneToMany
    private List<ParkingSpot> parkingSpots;
    
    @ManyToOne
    private ParkingLot parkingLot;
}
