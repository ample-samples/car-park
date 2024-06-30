package org.example.ParkingLot;

import org.example.ParkingSpot.ParkingSpot;
import org.example.ParkingSpot.ParkingSpotType;
import org.example.Vehicle.VehicleType;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

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

    // TODO: implement
    public Optional<VehicleType> emptySpot(int spotNumber) {
        ParkingSpot parkingSpot = parkingLot.get(spotNumber);
        VehicleType vehicleType = parkingSpot.getVehicleType();
        ParkingSpotType parkingSpotType = parkingSpot.parkingSpotType;
        if (vehicleType != null) {
            parkingSpot.emptySpot();
            switch (parkingSpotType) {
                case MOTORCYCLE:
                    motorcycleSpots[0]--;
                    break;
                case COMPACT:
                    compactSpots[0]--;
                    break;
                case REGULAR:
                    regularSpots[0]--;
                    break;
                case LARGE:
                    largeSpots[0]--;
                    break;
            }
            return Optional.of(vehicleType);
        }
        return null;
    }

    // TODO: implement
    public Optional<VehicleType> emptySpot(VehicleType vehicleType) {
        return null;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Motorcycle spots: " + this.motorcycleSpots[0] + " occupied, " + this.motorcycleSpots[1] + " total\n");
        stringBuilder.append("Compact spots: " + this.compactSpots[0] + " occupied, " + this.compactSpots[1] + " total\n");
        stringBuilder.append("Regular spots: " + this.regularSpots[0] + " occupied, " + this.regularSpots[1] + " total\n");
        stringBuilder.append("Large spots: " + this.largeSpots[0] + " occupied, " + this.largeSpots[1] + " total\n");
        stringBuilder.append("\n");

        for (Map.Entry<Integer, ParkingSpot> entry : parkingLot.entrySet()) {
            ParkingSpot parkingSpot = entry.getValue();
//            stringBuilder.append(parkingSpot.toString() + "\n");
            if (!parkingSpot.isFilled()) {
                stringBuilder.append(parkingSpot.parkingSpotType + ": Empty\n");
            } else {
                stringBuilder.append(parkingSpot.parkingSpotType.toString() + ": " + parkingSpot.getVehicleType() + "\n");
            };
        }
        return stringBuilder.toString();
    }
}
