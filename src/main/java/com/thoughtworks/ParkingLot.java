package com.thoughtworks;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    private List<Object> parkObject;
    private int capacity;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        parkObject = new ArrayList<>();
    }

    public boolean park(Object object) throws ParkingLotException, VehicleAlreadyParkedException {

        if (isAlreadyFull()) {
            if (isAlreadyParked(object)) {
                throw new VehicleAlreadyParkedException("Vehical already parked");
            }
            parkObject.add(object);
            return true;
        }
        throw new ParkingLotException("Parking lot full");
    }

    private boolean isAlreadyParked(Object object) {
        return parkObject.contains(object);
    }

    private boolean isAlreadyFull() {
        return parkObject.size() < capacity;
    }

    public Object unPark(Object object) throws VehicleNotParkedException {
        if (isAlreadyParked(object)) {
            parkObject.remove(object);
            return object;
        }
        throw new VehicleNotParkedException("Vehicle Not available");
    }

}





