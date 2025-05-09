
package com.example.DigitalParkingSystem.entity;

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
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String vehicleNumber;
    
    @Enumerated(EnumType.STRING)
    private VehicleType type;
}

