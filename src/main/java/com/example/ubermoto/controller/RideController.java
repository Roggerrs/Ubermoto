package com.example.ubermoto.controller;

import com.example.ubermoto.dto.RideRequestDTO;
import com.example.ubermoto.dto.RideResponseDTO;
import com.example.ubermoto.model.Ride;
import com.example.ubermoto.service.RideService;
import com.example.ubermoto.mapper.UberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rides")
@RequiredArgsConstructor
public class RideController {

    private final RideService service;
    private final UberMapper mapper;

    @PostMapping("/request")
    public ResponseEntity<RideResponseDTO> requestRide(@RequestBody RideRequestDTO request) {
        Ride ride = service.request(request);
        return ResponseEntity.ok(mapper.toDTO(ride));
    }

    @PostMapping("/{id}/complete")
    public ResponseEntity<RideResponseDTO> completeRide(@PathVariable Long id) {
        Ride ride = service.complete(id);
        return ResponseEntity.ok(mapper.toDTO(ride));
    }
}