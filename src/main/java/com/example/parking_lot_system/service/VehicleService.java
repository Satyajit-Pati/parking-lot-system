package com.example.parking_lot_system.service;

import com.example.parking_lot_system.model.Vehicle;
import com.example.parking_lot_system.model.VehicleType;
import com.example.parking_lot_system.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepo;

    public Vehicle registerOrGet(String licenseNumber, VehicleType vehicleType) {
        Vehicle vehicle = vehicleRepo.findByLicenseNumber(licenseNumber);
        if(vehicle == null){
            vehicle = new Vehicle();
            vehicle.setLicenseNumber(licenseNumber);
            vehicle.setVehicleType(vehicleType);
            return vehicleRepo.save(vehicle);
        }
        return vehicle;
    }
}
