package com.example.tickets.repository;

import com.example.tickets.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByTrain_OriginStation_StationNameAndTrain_DestinationStation_StationName(String origin, String destination);
}
