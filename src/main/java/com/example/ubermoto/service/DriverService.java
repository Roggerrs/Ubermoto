package com.example.ubermoto.service;

import com.example.ubermoto.model.Driver;
import java.util.List;

public interface DriverService {
    Driver create(Driver driver);
    List<Driver> listAll();
    Driver update(Long id, Driver driver);
}