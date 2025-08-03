package com.example.parking_lot_system.service.fee;

import com.example.parking_lot_system.model.VehicleType;

import java.time.LocalDateTime;

public interface CostCalculator {
    double calculateFee(VehicleType type, LocalDateTime entry, LocalDateTime exit);
}
