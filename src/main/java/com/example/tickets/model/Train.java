package com.example.tickets.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalTime;

@Entity
@Table(name = "Trains")
@Data
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "train_id")
    private Integer trainId;

    @Column(name = "train_number", nullable = false)
    private String trainNumber;

    @ManyToOne
    @JoinColumn(name = "origin_station", referencedColumnName = "station_id", foreignKey = @ForeignKey(name = "FK_origin_station"))
    private Station originStation;

    @ManyToOne
    @JoinColumn(name = "destination_station", referencedColumnName = "station_id", foreignKey = @ForeignKey(name = "FK_destination_station"))
    private Station destinationStation;

    @Column(name = "departure_time")
    private LocalTime departureTime;

    @Column(name = "arrival_time")
    private LocalTime arrivalTime;
}
