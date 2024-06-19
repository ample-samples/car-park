package org.example.ParkingLot;

import org.example.ParkingSpot.ParkingSpot;
import org.example.ParkingSpot.ParkingSpotType;
import org.example.Vehicle.VehicleType;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    final private int[] motorcycleSpots;
    final private int[] compactSpots;
    final private int[] regularSpots;
    final private int[] largeSpots;
    final private int[] totalSpots;

    final private HashMap<Integer, ParkingSpot> parkingLot;

    public ParkingLot(int motorcycleSpots, int compactSpots, int regularSpots, int largeSpots) {
        this.compactSpots = new int[] {0, compactSpots};
        this.largeSpots = new int [] {0, largeSpots};
        this.motorcycleSpots = new int [] {0, motorcycleSpots};
        this.regularSpots = new int [] {0, regularSpots};
        this.totalSpots = new int [] {0, compactSpots + motorcycleSpots + largeSpots + regularSpots};
        this.parkingLot = new HashMap<>(totalSpots[1]);
    }

    public boolean fillSpot(VehicleType vehicleType) {
        switch (vehicleType) {
            case MOTORCYCLE:
                if (motorcycleSpots[0] >= motorcycleSpots[1]) return false;

                for (Map.Entry<Integer, ParkingSpot> entry: parkingLot.entrySet()) {

                }
                motorcycleSpots[0]++;
                break;
        }
    }


    int[] parkingSpotAmounts = new int[]{};

    int spotCounter = 1;
        for (int i = 0; i < parkingSpotAmounts.length; i++) {
        for (int j = 0; j < parkingSpotAmounts[i]; j++) {
            switch (i) {
                case (0):
                    parkingLot.put(spotCounter, new ParkingSpot(ParkingSpotType.MOTORCYCLE, spotCounter));
                    break;
                case (1):
                    parkingLot.put(spotCounter, new ParkingSpot(ParkingSpotType.COMPACT, spotCounter));
                    break;
                case (2):
                    parkingLot.put(spotCounter, new ParkingSpot(ParkingSpotType.REGULAR, spotCounter));
                    break;
                case (3):
                    parkingLot.put(spotCounter, new ParkingSpot(ParkingSpotType.LARGE, spotCounter));
                    break;
            }
            spotCounter++;
        }
    }

        for (
    Map.Entry<Integer, ParkingSpot> entry : parkingLot.entrySet()) {
        System.out.println(entry.getValue());
    }
}
