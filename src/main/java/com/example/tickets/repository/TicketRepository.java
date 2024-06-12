package com.example.tickets.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tickets.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByTrain_OriginStation_StationNameAndTrain_DestinationStation_StationName(String origin, String destination);

    List<Ticket> findByTrain_OriginStation_LocationAndTrain_DestinationStation_Location(String origin,
            String destination);

}
