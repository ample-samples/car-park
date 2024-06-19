package org.example.ParkingSpot;

import org.example.Vehicle.VehicleType;

import java.util.Optional;

public class ParkingSpot {
    private boolean filled = false;
    private Optional<VehicleType> vehicleType = null;
    final int spotNumber;
    final ParkingSpotType parkingSpotType;

    public ParkingSpot(ParkingSpotType parkingSpotType, int spotNumber) {
        this.spotNumber = spotNumber;
        this.parkingSpotType = parkingSpotType;
    }

    public Optional<VehicleType> emptySpot() {
        if (vehicleType.isEmpty()) return null;

        Optional<VehicleType> leavingVehicle = vehicleType;
        vehicleType = null;
        return leavingVehicle;
    }

    public boolean fillSpot(VehicleType vehicleType) {
        // check if spot is full
        if (filled) {
            return false;
        }

        this.vehicleType = Optional.of(vehicleType);
        return true;
    }
}