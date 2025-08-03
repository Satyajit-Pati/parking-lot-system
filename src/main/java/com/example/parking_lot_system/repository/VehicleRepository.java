package com.example.parking_lot_system.repository;

import com.example.parking_lot_system.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
    boolean existsByLicenseNumber(String licenseNumber);
    Vehicle findByLicenseNumber(String licenseNumber);
}
