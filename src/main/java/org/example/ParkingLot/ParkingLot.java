package org.example.ParkingLot;

import org.example.ParkingSpot.ParkingSpot;
import org.example.ParkingSpot.ParkingSpotType;
import org.example.Vehicle.VehicleType;

import java.util.*;
import java.util.stream.Collectors;

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
                        }
                    }
                }
                break;
            case CAR:
                // TODO: Combine these loops, (worst case parkingLot.entrySet() is looped through twice)
                if (regularSpots[0] < regularSpots[1]) {
                    for (Map.Entry<Integer, ParkingSpot> entry : parkingLot.entrySet()) {
                        if (!entry.getValue().isFilled()) {
                            // TODO: -> ParkingLot.decrementParkingSpotCount()
                            if (entry.getValue().parkingSpotType == ParkingSpotType.REGULAR && entry.getValue().fillSpot(VehicleType.CAR)) {
                                regularSpots[0]++;
                                return true;
                            }
                        }
                    }
                } else if (compactSpots[0] < compactSpots[1]) {
                    for (Map.Entry<Integer, ParkingSpot> entry : parkingLot.entrySet()) {
                        if (!entry.getValue().isFilled()) {
                            // TODO: -> ParkingLot.decrementParkingSpotCount()
                            if (entry.getValue().parkingSpotType == ParkingSpotType.COMPACT && entry.getValue().fillSpot(VehicleType.CAR)) {
                                compactSpots[0]++;
                                return true;
                            }
                        }
                    }
                }
                break;
            case VAN:
                if (largeSpots[0] < largeSpots[1]) {
                    for (Map.Entry<Integer, ParkingSpot> entry : parkingLot.entrySet()) {
                        ParkingSpot parkingSpot = entry.getValue();
                        if (!parkingSpot.isFilled() && parkingSpot.parkingSpotType == ParkingSpotType.LARGE && parkingSpot.fillSpot(VehicleType.VAN)) {
                            largeSpots[0]++;
                            return true;
                        }
                    }
                    break;
                } else if (regularSpots[0] <= regularSpots[1] - 3) {
                    int consecutiveRegularSpots = 0;
                    Iterator<Map.Entry<Integer, ParkingSpot>> parkingSpots = parkingLot.entrySet().iterator();
                    for (int i = 1; parkingSpots.hasNext(); i++, parkingSpots.next()) {
                        ParkingSpot parkingSpot = parkingLot.get(i);
                        if (!parkingSpot.isFilled() && parkingSpot.parkingSpotType == ParkingSpotType.REGULAR) {
                            consecutiveRegularSpots++;
                        } else {
                            consecutiveRegularSpots = 0;
                        }
                        if (consecutiveRegularSpots == 3) {
                            parkingLot.get(i).fillSpot(VehicleType.VAN);
                            parkingLot.get(i - 1).fillSpot(VehicleType.VAN);
                            parkingLot.get(i - 2).fillSpot(VehicleType.VAN);
                            regularSpots[0] += 3;
                            break;
                        }
                    }
                }
        }
        System.out.println("There are no more " + vehicleType.toString().toLowerCase() + " spots left");
        return false;
    }

    public VehicleType emptySpot(int spotNumber) {
        ParkingSpot parkingSpot = parkingLot.get(spotNumber);
        VehicleType vehicleType = parkingSpot.getVehicleType();
        ParkingSpotType parkingSpotType = parkingSpot.parkingSpotType;
        if (vehicleType != VehicleType.EMPTY) {
            parkingSpot.emptySpot();
            decrementParkingSpotCount(parkingSpotType);
            return vehicleType;
        }
        return null;
    }

    public VehicleType emptySpot(VehicleType vehicleType) {
        for (int i = 1; i < parkingLot.size(); i++) {
            ParkingSpot parkingSpot = parkingLot.get(i);
            if (parkingSpot.isFilled() && parkingSpot.getVehicleType() == vehicleType) {
                parkingSpot.emptySpot();
                decrementParkingSpotCount(parkingSpot.parkingSpotType);
                return vehicleType;
            }
        }

        return null;
    }

    private void decrementParkingSpotCount(ParkingSpotType parkingSpotType) {
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
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\n");
        stringBuilder.append("Motorcycle spots: " + this.motorcycleSpots[0] + " occupied, " + this.motorcycleSpots[1] + " total\n");
        stringBuilder.append("Compact spots: " + this.compactSpots[0] + " occupied, " + this.compactSpots[1] + " total\n");
        stringBuilder.append("Regular spots: " + this.regularSpots[0] + " occupied, " + this.regularSpots[1] + " total\n");
        stringBuilder.append("Large spots: " + this.largeSpots[0] + " occupied, " + this.largeSpots[1] + " total\n");
        stringBuilder.append("\n");

        for (Map.Entry<Integer, ParkingSpot> entry : parkingLot.entrySet()) {
            ParkingSpot parkingSpot = entry.getValue();
//            stringBuilder.append(parkingSpot.toString() + "\n");
            stringBuilder.append(String.format("[%d] ", parkingSpot.getSpotNumber()));
            if (!parkingSpot.isFilled()) {
                stringBuilder.append(parkingSpot.parkingSpotType + ": Empty\n");
            } else {
                stringBuilder.append(parkingSpot.parkingSpotType.toString() + ": " + parkingSpot.getVehicleType() + "\n");
            }
            ;
        }
        return stringBuilder.toString();
    }
}