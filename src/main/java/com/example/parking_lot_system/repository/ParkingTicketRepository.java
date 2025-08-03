package com.example.parking_lot_system.repository;

import com.example.parking_lot_system.model.ParkingTicket;
import com.example.parking_lot_system.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParkingTicketRepository extends JpaRepository<ParkingTicket,Long> {
    Optional<ParkingTicket> findByVehicleAndExitTimeIsNull(Vehicle vehicle);
}
