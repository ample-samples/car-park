package org.example.ParkingSpot;

import org.example.Vehicle.VehicleType;

import java.util.Optional;

public class ParkingSpot {
    private boolean filled = false;
    private Optional<VehicleType> vehicleType = null;

    final int spotNumber;
    public final ParkingSpotType parkingSpotType;

    public int getSpotNumber() {
        return spotNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType.get();
    }

    public void setVehicleType(Optional<VehicleType> vehicleType) {
        this.vehicleType = vehicleType;
    }


    public ParkingSpot(ParkingSpotType parkingSpotType, int spotNumber) {
        this.spotNumber = spotNumber;
        this.parkingSpotType = parkingSpotType;
    }

    public boolean isFilled() {
        return filled;
    }

    public Optional<VehicleType> emptySpot() {
        if (vehicleType.isEmpty()) return null;

        Optional<VehicleType> leavingVehicle = vehicleType;
        vehicleType = null;
        return leavingVehicle;
    }

    public boolean fillSpot(VehicleType vehicleType) {
        // check if spot is full
        if (this.filled) {
            return false;
        }
        switch (vehicleType) {
            case MOTORCYCLE:
                this.vehicleType = Optional.of(vehicleType);
                this.filled = true;
                return true;
            case CAR:
                if (this.parkingSpotType != ParkingSpotType.MOTORCYCLE) {
                    this.vehicleType = Optional.of(vehicleType);
                    this.filled = true;
                    return true;
                }
                break;
            case VAN:
                if (this.parkingSpotType == ParkingSpotType.LARGE) {
                    this.vehicleType = Optional.of(vehicleType);
                    this.filled = true;
                    return true;
                }
                break;
        }
        return false;
    }

    @Override
    public String toString() {
        return "ParkingSpot{ " +
                "filled=" + filled +
                ", vehicleType=" + vehicleType +
                ", spotNumber=" + spotNumber +
                ", parkingSpotType=" + parkingSpotType +
                " }";
    }
}