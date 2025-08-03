package com.example.parking_lot_system.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class ParkingFloor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private int floorNumber;

    @OneToMany(mappedBy = "parkingFloor", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ParkingSpot> parkingSpots;

    @ManyToOne
    @JoinColumn(name = "lot_id")
    @JsonBackReference
    private ParkingLot parkingLot;

    public ParkingFloor() {

    }

    public ParkingFloor(Long id, int floorNumber, List<ParkingSpot> parkingSpots, ParkingLot parkingLot) {
        this.id = id;
        this.floorNumber = floorNumber;
        this.parkingSpots = parkingSpots;
        this.parkingLot = parkingLot;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public List<ParkingSpot> getParkingSpots() {
        return parkingSpots;
    }

    public void setParkingSpots(List<ParkingSpot> parkingSpots) {
        this.parkingSpots = parkingSpots;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }
}
