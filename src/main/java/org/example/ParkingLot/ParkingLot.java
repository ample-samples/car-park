package org.example.ParkingLot;

import org.example.ParkingSpot.ParkingSpot;
import org.example.ParkingSpot.ParkingSpotType;
import org.example.Vehicle.VehicleType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ParkingLot {
    final private int[] motorcycleSpots;
    final private int[] compactSpots;
    final private int[] regularSpots;
    final private int[] largeSpots;

    final private LinkedHashMap<Integer, ParkingSpot> parkingLot;

    public ParkingLot(int motorcycleSpots, int compactSpots, int regularSpots, int largeSpots) {
        // spots => { filledSpots, totalSpots }
        this.compactSpots = new int[]{0, compactSpots};
        this.largeSpots = new int[]{0, largeSpots};
        this.motorcycleSpots = new int[]{0, motorcycleSpots};
        this.regularSpots = new int[]{0, regularSpots};
        this.parkingLot = new LinkedHashMap<>(compactSpots + motorcycleSpots + largeSpots + regularSpots);

        int filledSpots = 0;
        for (int i = 0; i < this.motorcycleSpots[1]; i++) {
            this.parkingLot.put(filledSpots + 1, new ParkingSpot(ParkingSpotType.MOTORCYCLE, filledSpots + 1));
            filledSpots++;
        }
        for (int i = 0; i < this.compactSpots[1]; i++) {
            this.parkingLot.put(filledSpots + 1, new ParkingSpot(ParkingSpotType.COMPACT, filledSpots + 1));
            filledSpots++;
        }
        for (int i = 0; i < this.regularSpots[1]; i++) {
            this.parkingLot.put(filledSpots + 1, new ParkingSpot(ParkingSpotType.REGULAR, filledSpots + 1));
            filledSpots++;
        }
        for (int i = 0; i < this.largeSpots[1]; i++) {
            this.parkingLot.put(filledSpots + 1, new ParkingSpot(ParkingSpotType.LARGE, filledSpots + 1));
            filledSpots++;
        }
    }

    public boolean fillSpot(VehicleType vehicleType) {
        switch (vehicleType) {
            case MOTORCYCLE:
                if (motorcycleSpots[0] >= motorcycleSpots[1]) break;

                for (Map.Entry<Integer, ParkingSpot> entry : parkingLot.entrySet()) {
                    if (!entry.getValue().isFilled()) {
                        if (entry.getValue().fillSpot(VehicleType.MOTORCYCLE)) {
                            motorcycleSpots[0]++;
                            return true;
                        };
                    }
                }
                break;
            case CAR:
                // TODO: Combine these loops, (worst case parkingLot.entrySet() is looped through twice)
                if (regularSpots[0] < regularSpots[1]) {
                    for (Map.Entry<Integer, ParkingSpot> entry : parkingLot.entrySet()) {
                        if (!entry.getValue().isFilled()) {
                            if (entry.getValue().parkingSpotType == ParkingSpotType.REGULAR && entry.getValue().fillSpot(VehicleType.CAR)) {
                                regularSpots[0]++;
                                return true;
                            };
                        }
                    }
                } else if (compactSpots[0] < compactSpots[1]) {
                    for (Map.Entry<Integer, ParkingSpot> entry : parkingLot.entrySet()) {
                        if (!entry.getValue().isFilled()) {
                            if (entry.getValue().parkingSpotType == ParkingSpotType.COMPACT && entry.getValue().fillSpot(VehicleType.CAR)) {
                                compactSpots[0]++;
                                return true;
                            };
                        }
                    }
                }
                break;
            case VAN:
                if (largeSpots[0] >= largeSpots[1]) break;

                for (Map.Entry<Integer, ParkingSpot> entry : parkingLot.entrySet()) {
                    if (!entry.getValue().isFilled()) {
                        if (entry.getValue().fillSpot(VehicleType.VAN)) {
                            largeSpots[0]++;
                            return true;
                        };
                    }
                }
                break;
        }
        System.out.println("There are no more " + vehicleType.toString().toLowerCase() + " spots left" );
        return false;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Arrays.toString(this.motorcycleSpots) + "\n");
        stringBuilder.append(Arrays.toString(this.compactSpots) + "\n");
        stringBuilder.append(Arrays.toString(this.regularSpots) + "\n");
        stringBuilder.append(Arrays.toString(this.largeSpots) + "\n");
        for (Map.Entry<Integer, ParkingSpot> entry : parkingLot.entrySet()) {
            ParkingSpot parkingSpot = entry.getValue();
            stringBuilder.append(parkingSpot.toString() + "\n");
//            if (!parkingSpot.isFilled()) {
//                stringBuilder.append(parkingSpot.parkingSpotType + ": Empty\n");
//            } else {
//                stringBuilder.append(parkingSpot.parkingSpotType.toString() + ": " + parkingSpot.getVehicleType() + "\n");
//            };
        }
        return stringBuilder.toString();
    }


//    int[] parkingSpotAmounts = new int[]{};
//
//    int spotCounter = 1;
//        for( int i = 0; i<parkingSpotAmounts.length;i++) {
//        for (int j = 0; j < parkingSpotAmounts[i]; j++) {
//            switch (i) {
//                case (0):
//                    parkingLot.put(spotCounter, new ParkingSpot(ParkingSpotType.MOTORCYCLE, spotCounter));
//                    break;
//                case (1):
//                    parkingLot.put(spotCounter, new ParkingSpot(ParkingSpotType.COMPACT, spotCounter));
//                    break;
//                case (2):
//                    parkingLot.put(spotCounter, new ParkingSpot(ParkingSpotType.REGULAR, spotCounter));
//                    break;
//                case (3):
//                    parkingLot.put(spotCounter, new ParkingSpot(ParkingSpotType.LARGE, spotCounter));
//                    break;
//            }
//            spotCounter++;
//        }
//    }
//
//        for(Map.Entry<Integer, ParkingSpot> entry :parkingLot.entrySet()) {
//
//    }
//
//    {
//        System.out.println(entry.getValue());
//    }
}
