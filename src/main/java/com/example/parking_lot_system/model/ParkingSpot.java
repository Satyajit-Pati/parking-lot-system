package com.example.parking_lot_system.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class ParkingSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String spotNumber;

    @OneToOne
    @JoinColumn(name = "vehicle_id")
    @JsonIgnore
    private Vehicle vehicle;

    @NotNull
    @Enumerated(EnumType.STRING)
    private SpotType spotType;


    private boolean isOccupied;

    @ManyToOne
    @JoinColumn(name = "floor_id")
    @JsonBackReference
    private ParkingFloor parkingFloor;

    public ParkingSpot() {
    }

    public ParkingSpot(Long id, String spotNumber, Vehicle vehicle, SpotType spotType, boolean isOccupied, ParkingFloor parkingFloor) {
        this.id = id;
        this.spotNumber = spotNumber;
        this.vehicle = vehicle;
        this.spotType = spotType;
        this.isOccupied = isOccupied;
        this.parkingFloor = parkingFloor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpotNumber() {
        return spotNumber;
    }

    public void setSpotNumber(String spotNumber) {
        this.spotNumber = spotNumber;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public SpotType getSpotType() {
        return spotType;
    }

    public void setSpotType(SpotType spotType) {
        this.spotType = spotType;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public ParkingFloor getParkingFloor() {
        return parkingFloor;
    }

    public void setParkingFloor(ParkingFloor parkingFloor) {
        this.parkingFloor = parkingFloor;
    }
}
