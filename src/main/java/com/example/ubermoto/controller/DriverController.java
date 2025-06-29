package com.example.ubermoto.controller;

import com.example.ubermoto.dto.DriverDTO;
import com.example.ubermoto.model.Driver;
import com.example.ubermoto.service.DriverService;
import com.example.ubermoto.mapper.UberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService service;
    private final UberMapper mapper;

    @PostMapping
    public ResponseEntity<DriverDTO> create(@RequestBody DriverDTO dto) {
        Driver saved = service.create(mapper.toEntity(dto));
        return ResponseEntity.ok(mapper.toDTO(saved));
    }

    @GetMapping
    public List<DriverDTO> listAll() {
        return service.listAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DriverDTO> update(@PathVariable Long id, @RequestBody DriverDTO dto) {
        Driver updated = service.update(id, mapper.toEntity(dto));
        return ResponseEntity.ok(mapper.toDTO(updated));
    }
}