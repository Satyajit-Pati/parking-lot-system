package com.example.parking_lot_system;

import com.example.parking_lot_system.model.ParkingFloor;
import com.example.parking_lot_system.model.ParkingLot;
import com.example.parking_lot_system.model.ParkingSpot;
import com.example.parking_lot_system.model.SpotType;
import com.example.parking_lot_system.repository.ParkingFloorRepository;
import com.example.parking_lot_system.repository.ParkingLotRepository;
import com.example.parking_lot_system.repository.ParkingSpotRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer {

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @Autowired
    private ParkingFloorRepository parkingFloorRepository;

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    @PostConstruct
    public void initializeData() {
        if(parkingLotRepository.count() > 0) {
            return;
        }
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName("Main Parking Lot");
        parkingLot.setLocation("Bengaluru");

        ParkingFloor floor1= new ParkingFloor();
        floor1.setFloorNumber(1);
        floor1.setParkingLot(parkingLot);

        ParkingFloor floor2 = new ParkingFloor();
        floor2.setFloorNumber(2);
        floor2.setParkingLot(parkingLot);

        parkingLot.setParkingFloors(List.of(floor1,floor2));

        parkingLotRepository.save(parkingLot);

        List<ParkingSpot> spots = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            spots.add(createSpot("S" + i, SpotType.SMALL, floor1));
        }
        for (int i = 1; i <= 2; i++) {
            spots.add(createSpot("M" + i, SpotType.MEDIUM, floor1));
        }

        // Add parking spots to floor2
        for (int i = 1; i <= 2; i++) {
            spots.add(createSpot("L" + i, SpotType.LARGE, floor2));
        }

        parkingSpotRepository.saveAll(spots);
    }

    private ParkingSpot createSpot(String number, SpotType spotType, ParkingFloor floor) {
        ParkingSpot spot = new ParkingSpot();
        spot.setSpotNumber(number);
        spot.setSpotType(spotType);
        spot.setParkingFloor(floor);
        spot.setOccupied(false);
        return spot;
    }
}

