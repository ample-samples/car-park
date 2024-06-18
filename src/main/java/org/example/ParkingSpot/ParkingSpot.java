package org.example.ParkingSpot;

import org.example.Vehicle.VehicleType;

import java.util.Optional;

public class ParkingSpot {
    private boolean filled = false;
    private Optional<VehicleType> vehicleType = null;

    public VehicleType removeVehicle() {
        return null;
    }

    public void fillSpot(VehicleType vehicleType) {
        this.vehicleType = Optional.of(vehicleType);
    }
}