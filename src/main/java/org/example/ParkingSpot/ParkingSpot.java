package org.example.ParkingSpot;

import org.example.Vehicle.VehicleType;

import java.util.Optional;

public interface ParkingSpot {
    boolean filled = false;
    Optional<VehicleType> vehicleType = null;

    public VehicleType removeVehicle();

    public void fillSpot();
}