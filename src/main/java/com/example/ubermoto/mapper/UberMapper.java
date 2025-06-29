package com.example.ubermoto.mapper;

import com.example.ubermoto.dto.DriverDTO;
import com.example.ubermoto.dto.RideResponseDTO;
import com.example.ubermoto.model.Driver;
import com.example.ubermoto.model.Ride;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UberMapper {

    // Driver ↔ DTO
    DriverDTO toDTO(Driver entity);
    Driver toEntity(DriverDTO dto);

    // Ride → ResponseDTO
    @Mapping(source = "id", target = "rideId")
    RideResponseDTO toDTO(Ride ride);
}