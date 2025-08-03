package com.example.parking_lot_system.repository;

import com.example.parking_lot_system.model.ParkingSpot;
import com.example.parking_lot_system.model.SpotType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingSpotRepository extends JpaRepository<ParkingSpot,Long> {
    List<ParkingSpot> findBySpotTypeAndIsOccupiedFalse(SpotType spotType);
}
