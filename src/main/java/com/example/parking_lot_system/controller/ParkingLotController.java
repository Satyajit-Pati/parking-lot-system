package com.example.parking_lot_system.controller;

import com.example.parking_lot_system.model.ParkingTicket;
import com.example.parking_lot_system.model.VehicleType;
import com.example.parking_lot_system.service.ParkingTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/parking")
public class ParkingLotController {

        @Autowired
        private ParkingTicketService ticketService;

        @PostMapping("/checkin")
        public ParkingTicket checkIn(@RequestParam String licenseNumber,
                                     @RequestParam VehicleType vehicleType) {
            return ticketService.checkIn(licenseNumber,vehicleType);
        }

        @PostMapping("/checkout")
        public ParkingTicket checkOut(@RequestParam String licenseNumber) {
            return ticketService.checkOut(licenseNumber);
        }
    }
