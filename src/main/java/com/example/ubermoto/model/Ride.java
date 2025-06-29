package com.example.ubermoto.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "ride")
@Data
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;

    private Double startLat;
    private Double startLng;

    private Double endLat;
    private Double endLng;

    private String status;

    private LocalDateTime requestedAt;
    private LocalDateTime completedAt;
}