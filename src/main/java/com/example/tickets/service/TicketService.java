package com.example.tickets.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tickets.model.Ticket;
import com.example.tickets.repository.TicketRepository;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> findTickets(String origin, String destination) {
        return ticketRepository.findByTrain_OriginStation_StationNameAndTrain_DestinationStation_StationName(origin, destination);
    }

    public List<Ticket> findAllTickets() {
        return ticketRepository.findAll();
    }

    

}
