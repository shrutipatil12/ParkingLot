package com.thoughtworks;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot extends Owner {

    private List<Object> parkObject = new ArrayList<>();
    private int capacity;
    private Owner owner;

    public ParkingLot(int capacity, Owner owner) {
        this.capacity = capacity;
        this.owner = owner;
    }

    public void park(Object object) throws ParkingLotIsFullException, VehicleAlreadyParkedException {

        if (isSpaceAvailable()) {
            if (isAlreadyParked(object)) {
                throw new VehicleAlreadyParkedException();
            }
            parkObject.add(object);
            this.informIfCapacityFull(); // Inform parking lot is full
        } else {
            throw new ParkingLotIsFullException();
        }
    }

    private boolean isAlreadyParked(Object object) {
        return parkObject.contains(object);
    }

    private boolean isSpaceAvailable() {
        return parkObject.size() < capacity;
    }

    public Object unPark(Object object) throws VehicleNotParkedException {
        if (isAlreadyParked(object)) {
            parkObject.remove(object);
            if (parkObject.size() == capacity - 1) {
                owner.informParkingLotIsNowAvailable(); // inform parking lot is available again
            }
            return object;
        }
        throw new VehicleNotParkedException();
    }

    private void informIfCapacityFull() {
        if (parkObject.size() == capacity) {
            owner.informParkingLotFull();
        }

    }
}





