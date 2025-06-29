package com.example.ubermoto.repository;

import com.example.ubermoto.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    @Query("""
  SELECT d FROM Driver d
   WHERE d.available = true
   ORDER BY SQRT((d.latitude - :lat)*(d.latitude - :lat)
                + (d.longitude - :lng)*(d.longitude - :lng))
""")
    List<Driver> findNearestAvailable(double lat, double lng);

}