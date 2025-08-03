package com.example.parking_lot_system.repository;

import com.example.parking_lot_system.model.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingLotRepository extends JpaRepository<ParkingLot,Long> {

}
