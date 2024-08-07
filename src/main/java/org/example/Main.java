package org.example;

import org.example.ParkingLot.ParkingLot;
import org.example.Vehicle.VehicleType;

public class Main {
    public static void main(String[] args) {
        int motorcycleSpots = 5;
        int compactSpots = 10;
        int regularSpots = 7;
        int largeSpots = 5;

        ParkingLot parkingLot = new ParkingLot(motorcycleSpots, compactSpots, regularSpots, largeSpots);
//        for (int i = 0; i < 7; i++) parkingLot.fillSpot(VehicleType.MOTORCYCLE);
        for (int i = 0; i < 2; i++) parkingLot.fillSpot(VehicleType.CAR);
        for (int i = 0; i < 6; i++) parkingLot.fillSpot(VehicleType.VAN);
        for (int i = 0; i < 6; i++) parkingLot.emptySpot(VehicleType.VAN);

//        parkingLot.emptySpot(4);
//        parkingLot.emptySpot(1);
//        parkingLot.emptySpot(2);
        System.out.println(parkingLot);
    }
}