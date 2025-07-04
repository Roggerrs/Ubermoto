package com.example.ubermoto.controller;

import com.example.ubermoto.dto.DriverDTO;
import com.example.ubermoto.model.Driver;
import com.example.ubermoto.service.DriverService;
import com.example.ubermoto.mapper.UberMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Motoristas", description = "Operações de gerenciamento de motoristas")
@RestController
@RequestMapping("/api/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService service;
    private final UberMapper mapper;

    @Operation(summary = "Listar todos os motoristas")
    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public List<DriverDTO> listAll() {
        return service.listAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Buscar motorista por ID")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<DriverDTO> getById(@PathVariable Long id) {
        Driver driver = service.findById(id);
        return ResponseEntity.ok(mapper.toDTO(driver));
    }

    @Operation(summary = "Criar um novo motorista")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<DriverDTO> create(@RequestBody DriverDTO dto) {
        Driver saved = service.create(mapper.toEntity(dto));
        return ResponseEntity.ok(mapper.toDTO(saved));
    }

    @Operation(summary = "Atualizar motorista existente")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<DriverDTO> update(
            @PathVariable Long id,
            @RequestBody DriverDTO dto) {
        Driver updated = service.update(id, mapper.toEntity(dto));
        return ResponseEntity.ok(mapper.toDTO(updated));
    }

    @Operation(summary = "Excluir motorista")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}