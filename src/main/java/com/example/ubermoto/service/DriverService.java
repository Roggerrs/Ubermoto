// src/main/java/com/example/ubermoto/service/DriverService.java
package com.example.ubermoto.service;

import com.example.ubermoto.model.Driver;
import java.util.List;

public interface DriverService {
    Driver create(Driver driver);
    List<Driver> listAll();
    Driver findById(Long id);
    Driver update(Long id, Driver driver);
    void delete(Long id);
    List<Driver> findNearest(double lat, double lng);
}