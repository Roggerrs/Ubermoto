package com.example.ubermoto.repository;

import com.example.ubermoto.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    /**
     * Retorna todos os motoristas disponíveis, ordenados pela distância
     * (quadrado da distância) em relação ao ponto (lat, lng).
     */
    @Query("""
      SELECT d
        FROM Driver d
       WHERE d.available = true
       ORDER BY
         (d.latitude  - :lat) * (d.latitude  - :lat) +
         (d.longitude - :lng) * (d.longitude - :lng)
    """)
    List<Driver> findNearestAvailable(
            @Param("lat") double lat,
            @Param("lng") double lng
    );
}