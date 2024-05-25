package com.example.tickets.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "Tickets")
@Data
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Integer ticketId;

    @ManyToOne
    @JoinColumn(name = "train_id", referencedColumnName = "train_id", foreignKey = @ForeignKey(name = "FK_train_id"))
    private Train train;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(name = "seat_type", nullable = false)
    private SeatType seatType;

    @Column(name = "remaining_seats", nullable = false)
    private Integer remainingSeats;
    
    public enum SeatType {
        BUSINESS_CLASS, FIRST_CLASS, SECOND_CLASS
    }
}
