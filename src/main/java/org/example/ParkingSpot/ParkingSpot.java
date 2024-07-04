package org.example.ParkingSpot;

import org.example.Vehicle.VehicleType;

import java.util.Optional;

public class ParkingSpot {
    private boolean filled = false;
    private VehicleType vehicleType = VehicleType.EMPTY;

    final int spotNumber;
    public final ParkingSpotType parkingSpotType;

    public int getSpotNumber() {
        return spotNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }


    public ParkingSpot(ParkingSpotType parkingSpotType, int spotNumber) {
        this.spotNumber = spotNumber;
        this.parkingSpotType = parkingSpotType;
    }

    public boolean isFilled() {
        return filled;
    }

    public VehicleType emptySpot() {
        if (vehicleType == VehicleType.EMPTY) return VehicleType.EMPTY;

        VehicleType leavingVehicle = vehicleType;
        vehicleType = VehicleType.EMPTY;
        this.filled = false;
        return leavingVehicle;
    }

    public boolean fillSpot(VehicleType vehicleType) {
        if (this.filled) {
            return false;
        }
        switch (vehicleType) {
            case MOTORCYCLE:
                this.vehicleType = vehicleType;
                this.filled = true;
                return true;
            case CAR:
                if (this.parkingSpotType != ParkingSpotType.MOTORCYCLE) {
                    this.vehicleType = vehicleType;
                    this.filled = true;
                    return true;
                }
                break;
            case VAN:
                if (this.parkingSpotType == ParkingSpotType.LARGE || this.parkingSpotType == ParkingSpotType.REGULAR) {
                    this.vehicleType = (vehicleType);
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