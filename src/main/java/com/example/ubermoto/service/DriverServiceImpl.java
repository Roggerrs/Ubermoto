package com.example.ubermoto.service;

import com.example.ubermoto.exception.ResourceNotFoundException;
import com.example.ubermoto.model.Driver;
import com.example.ubermoto.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverRepository repository;

    @Override
    public Driver create(Driver driver) {
        // Todo novo motorista inicia como disponível
        driver.setAvailable(true);
        return repository.save(driver);
    }

    @Override
    public List<Driver> listAll() {
        return repository.findAll();
    }

    @Override
    public Driver update(Long id, Driver driver) {
        // Busca o motorista existente ou lança exceção
        Driver existing = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Driver não encontrado com id " + id));

        // Atualiza apenas os campos permitidos
        existing.setName(driver.getName());
        existing.setLatitude(driver.getLatitude());
        existing.setLongitude(driver.getLongitude());
        existing.setAvailable(driver.getAvailable());

        return repository.save(existing);
    }
}