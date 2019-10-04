package com.thoughtworks;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot extends Owner {

    private List<Object> parkObject = new ArrayList<>();
    private int capacity;
    private Owner owner;
    int count = 0;

    public ParkingLot(int capacity, Owner owner) {
        this.capacity = capacity;
        this.owner = owner;
    }

    public boolean park(Object object) throws ParkingLotIsFullException, VehicleAlreadyParkedException {

        if (isAlreadyFull()) {
            if (isAlreadyParked(object)) {
                throw new VehicleAlreadyParkedException();
            }
            parkObject.add(object);
            this.informIfCapacityFull();

            return true;
        }
        throw new ParkingLotIsFullException();
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
        throw new VehicleNotParkedException();
    }

    private void informIfCapacityFull() {
        if (parkObject.size() == capacity) {
            owner.inform();
        }

    }
}





