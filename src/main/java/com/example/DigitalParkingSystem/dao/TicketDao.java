
package com.example.DigitalParkingSystem.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.DigitalParkingSystem.entity.ParkingLot;
import com.example.DigitalParkingSystem.entity.Ticket;
import com.example.DigitalParkingSystem.repository.TicketRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class TicketDao {
    @Autowired
    private TicketRepository ticketRepo;

}
