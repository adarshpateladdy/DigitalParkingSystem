package com.example.DigitalParkingSystem.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.DigitalParkingSystem.entity.Ticket;
import com.example.DigitalParkingSystem.repository.TicketRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TicketDao {
    @Autowired
    private TicketRepository ticketRepo;
    
    public Ticket saveTicket(Ticket ticket) {
        return ticketRepo.save(ticket);
    }
    
    public Optional<Ticket> getTicketById(Long id) {
        return ticketRepo.findById(id);
    }
    
    public List<Ticket> getAllTickets() {
        return ticketRepo.findAll();
    }
    
    public List<Ticket> getActiveTickets() {
        return ticketRepo.findByExitTimeIsNull();
    }
    
    public void deleteTicket(Ticket ticket) {
        ticketRepo.delete(ticket);
    }
}