package com.example.ubermoto.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RideResponseDTO {
    private Long rideId;
    private DriverDTO driver;
    private String status;
    private LocalDateTime requestedAt;
    private LocalDateTime completedAt;
}