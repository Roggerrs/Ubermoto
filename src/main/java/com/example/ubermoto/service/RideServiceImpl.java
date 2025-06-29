package com.example.ubermoto.service;

import com.example.ubermoto.dto.RideRequestDTO;
import com.example.ubermoto.exception.ResourceNotFoundException;
import com.example.ubermoto.model.Driver;
import com.example.ubermoto.model.Ride;
import com.example.ubermoto.repository.DriverRepository;
import com.example.ubermoto.repository.RideRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class RideServiceImpl implements RideService {

    private final DriverRepository driverRepo;
    private final RideRepository rideRepo;

    @Override
    public Ride request(RideRequestDTO req) {
        // 1) Encontra o motorista disponível mais próximo direto no banco
        Driver nearest = driverRepo
                .findNearestAvailable(req.getStartLat(), req.getStartLng())
                .stream()
                .findFirst()
                .orElseThrow(() ->
                        new ResourceNotFoundException("Nenhum motorista disponível no momento")
                );

        // 2) Bloqueia o driver
        nearest.setAvailable(false);
        driverRepo.save(nearest);

        // 3) Cria e salva a corrida
        Ride ride = new Ride();
        ride.setDriver(nearest);
        ride.setStartLat(req.getStartLat());
        ride.setStartLng(req.getStartLng());
        ride.setEndLat(req.getEndLat());
        ride.setEndLng(req.getEndLng());
        ride.setStatus("ONGOING");
        ride.setRequestedAt(LocalDateTime.now());

        return rideRepo.save(ride);
    }

    @Override
    public Ride complete(Long rideId) {
        // 1) Busca a corrida ou lança erro
        Ride ride = rideRepo.findById(rideId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Ride não encontrado com id " + rideId)
                );

        // 2) Atualiza status e timestamp
        ride.setStatus("COMPLETED");
        ride.setCompletedAt(LocalDateTime.now());
        rideRepo.save(ride);

        // 3) Libera o motorista
        Driver driver = ride.getDriver();
        driver.setAvailable(true);
        driverRepo.save(driver);

        return ride;
    }
}