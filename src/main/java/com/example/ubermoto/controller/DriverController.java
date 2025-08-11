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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Tag(name = "Motoristas", description = "Operações de gerenciamento de motoristas")
@RestController
@RequestMapping("/api/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService service;
    private final UberMapper mapper;
    private static final Logger logger = LoggerFactory.getLogger(DriverController.class);

    @Operation(summary = "Listar todos os motoristas")
    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public List<DriverDTO> listAll() {
        logger.trace("TRACE: Iniciando listagem de motoristas");
        logger.debug("DEBUG: Chamando service.listAll()");
        List<DriverDTO> result = service.listAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
        logger.info("INFO: Retornando lista de motoristas com {} registros", result.size());
        return result;
    }

    @Operation(summary = "Buscar motorista por ID")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<DriverDTO> getById(@PathVariable Long id) {
        logger.trace("TRACE: Iniciando busca de motorista por ID");
        logger.debug("DEBUG: Buscando motorista com ID {}", id);
        try {
            Driver driver = service.findById(id);
            logger.info("INFO: Motorista encontrado: {}", driver.getName());
            return ResponseEntity.ok(mapper.toDTO(driver));
        } catch (Exception e) {
            logger.warn("WARN: Motorista com ID {} não encontrado", id);
            logger.error("ERROR: Erro ao buscar motorista: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Operation(summary = "Criar um novo motorista")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<DriverDTO> create(@RequestBody DriverDTO dto) {
        logger.trace("TRACE: Iniciando criação de motorista");
        logger.debug("DEBUG: Dados recebidos para criação: {}", dto);
        Driver saved = service.create(mapper.toEntity(dto));
        logger.info("INFO: Motorista criado com ID {}", saved.getId());
        return ResponseEntity.ok(mapper.toDTO(saved));
    }

    @Operation(summary = "Atualizar motorista existente")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<DriverDTO> update(@PathVariable Long id, @RequestBody DriverDTO dto) {
        logger.trace("TRACE: Iniciando atualização de motorista");
        logger.debug("DEBUG: Atualizando motorista ID {} com dados: {}", id, dto);
        Driver updated = service.update(id, mapper.toEntity(dto));
        logger.info("INFO: Motorista atualizado: {}", updated.getName());
        return ResponseEntity.ok(mapper.toDTO(updated));
    }

    @Operation(summary = "Excluir motorista")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        logger.trace("TRACE: Iniciando exclusão de motorista");
        logger.debug("DEBUG: Excluindo motorista com ID {}", id);
        try {
            service.delete(id);
            logger.info("INFO: Motorista com ID {} excluído com sucesso", id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.warn("WARN: Falha ao excluir motorista com ID {}", id);
            logger.error("ERROR: Erro ao excluir motorista: {}", e.getMessage(), e);
            throw e;
        }
    }
}