package org.example;

import org.example.ParkingSpot.ParkingSpot;
import org.example.ParkingSpot.ParkingSpotType;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        int motorcycleSpots = 5;
        int compactSpots = 10;
        int regularSpots = 20;
        int largeSpots = 5;
        int totalSpots = motorcycleSpots + compactSpots + regularSpots + largeSpots;

        HashMap<Integer, ParkingSpot> parkingLot = new HashMap<>(totalSpots);

        int[] parkingSpotAmounts = new int[]{motorcycleSpots, compactSpots, regularSpots, largeSpots};

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

        for (Map.Entry<Integer, ParkingSpot> entry : parkingLot.entrySet()) {
            System.out.println(entry.getValue());
        }
    }
}