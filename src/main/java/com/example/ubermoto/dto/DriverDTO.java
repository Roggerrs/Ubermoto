package com.example.ubermoto.dto;

import lombok.Data;

@Data
public class DriverDTO {
    private Long id;
    private String name;
    private Double latitude;
    private Double longitude;
    private Boolean available;
}