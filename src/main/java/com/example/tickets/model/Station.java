package com.example.tickets.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Stations")
@Data
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "station_id")
    private Integer stationId;

    @Column(name = "station_name", nullable = false)
    private String stationName;

    @Column(name = "location")
    private String location;
}



