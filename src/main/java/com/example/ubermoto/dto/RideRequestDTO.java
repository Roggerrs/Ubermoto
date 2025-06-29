package com.example.ubermoto.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
public class RideRequestDTO {

    @NotNull
    private Double startLat;

    @NotNull
    private Double startLng;

    @NotNull
    private Double endLat;

    @NotNull
    private Double endLng;
}