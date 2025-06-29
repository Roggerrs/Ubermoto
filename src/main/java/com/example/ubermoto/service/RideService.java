package com.example.ubermoto.service;

import com.example.ubermoto.dto.RideRequestDTO;
import com.example.ubermoto.model.Ride;

public interface RideService {
    /**
     * Solicita uma corrida: encontra o motorista disponível mais próximo,
     * bloqueia o motorista e cria a entidade Ride.
     */
    Ride request(RideRequestDTO request);

    /**
     * Finaliza uma corrida em andamento: libera o motorista e marca a corrida como COMPLETED.
     */
    Ride complete(Long rideId);
}