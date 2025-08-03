package com.example.parking_lot_system.service;

import com.example.parking_lot_system.model.ParkingSpot;
import com.example.parking_lot_system.model.ParkingTicket;
import com.example.parking_lot_system.model.Vehicle;
import com.example.parking_lot_system.model.VehicleType;
import com.example.parking_lot_system.repository.ParkingSpotRepository;
import com.example.parking_lot_system.repository.ParkingTicketRepository;
import com.example.parking_lot_system.repository.VehicleRepository;
import com.example.parking_lot_system.service.allocator.SpotAllocator;
import com.example.parking_lot_system.service.fee.CostCalculator;
import com.example.parking_lot_system.service.fee.FlatFeeCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ParkingTicketService {
    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private SpotAllocator spotAllocator;

    @Autowired
    private FlatFeeCalculator costCalculator;

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    @Autowired
    private ParkingTicketRepository parkingTicketRepository;

    @Autowired
    private VehicleRepository vehicleRepository;


    public ParkingTicket checkIn(String licenseNumber, VehicleType vehicleType) {
        Vehicle vehicle = vehicleService.registerOrGet(licenseNumber, vehicleType);
        ParkingSpot parkingSpot = spotAllocator.allocateSpot(vehicle.getVehicleType());

        if(parkingTicketRepository.findByVehicleAndExitTimeIsNull(vehicle).isPresent()){
            throw new RuntimeException("Vehicle is already parked");
        }

        if(parkingSpot == null){
            throw new RuntimeException("No available parking spot for vehicle type: " + vehicleType);
        }

        parkingSpot.setOccupied(true);
        parkingSpot.setVehicle(vehicle);
        parkingSpotRepository.save(parkingSpot);

        ParkingTicket ticket = new ParkingTicket();
        ticket.setVehicle(vehicle);
        ticket.setParkingSpot(parkingSpot);
        ticket.setEntryTime(LocalDateTime.now());

        return parkingTicketRepository.save(ticket);
    }

    public ParkingTicket checkOut(String licenseNumber) {
        Vehicle vehicle = vehicleRepository.findByLicenseNumber(licenseNumber);
        if(vehicle == null) {
            throw new RuntimeException("Vehicle not found with license number: " + licenseNumber);
        }

        ParkingTicket ticket = parkingTicketRepository.findByVehicleAndExitTimeIsNull(vehicle).
                orElseThrow(() -> new RuntimeException("No active parking ticket found for vehicle"));

        ticket.setExitTime(LocalDateTime.now());
        double fee = costCalculator.calculateFee(vehicle.getVehicleType(), ticket.getEntryTime(), ticket.getExitTime());
        ticket.setTotalFee(fee);
        parkingTicketRepository.save(ticket);

        ParkingSpot spot = ticket.getParkingSpot();
        spot.setOccupied(false);
        spot.setVehicle(null);
        parkingSpotRepository.save(spot);

        return ticket;
    }
}
