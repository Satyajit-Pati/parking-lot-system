package com.example.parking_lot_system.service.fee;

import com.example.parking_lot_system.model.VehicleType;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class FlatFeeCalculator implements CostCalculator{

    @Override
    public double calculateFee(VehicleType type, LocalDateTime entry, LocalDateTime exit) {
        long hours = Math.max(Duration.between(entry,exit).toHours(),1);
        return switch (type){
            case TWO_WHEELER -> hours * 10.0;
            case THREE_WHEELER -> hours * 15.0;
            case CAR -> hours * 20.0;
            case BUS -> hours * 50.0;
            case TRUCK -> hours * 70.0;
        };
    }
}
