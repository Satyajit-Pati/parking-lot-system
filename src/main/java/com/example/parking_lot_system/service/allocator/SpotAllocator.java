package com.example.parking_lot_system.service.allocator;

import com.example.parking_lot_system.model.ParkingSpot;
import com.example.parking_lot_system.model.SpotType;
import com.example.parking_lot_system.model.VehicleType;
import com.example.parking_lot_system.repository.ParkingSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SpotAllocator {

    @Autowired
    private ParkingSpotRepository spotRepository;

    public ParkingSpot allocateSpot(VehicleType vehicleType) {
        SpotType requiredSpotType = mapVehicleToSpotType(vehicleType);
        List<ParkingSpot> spots = spotRepository.findBySpotTypeAndIsOccupiedFalse(requiredSpotType);
        return spots.isEmpty() ? null : spots.get(0);
    }

    private SpotType mapVehicleToSpotType(VehicleType vehicleType) {
       return switch (vehicleType) {
           case TWO_WHEELER -> SpotType.SMALL;
           case THREE_WHEELER, CAR -> SpotType.MEDIUM;
           case BUS, TRUCK -> SpotType.LARGE;
       };
    }
}
