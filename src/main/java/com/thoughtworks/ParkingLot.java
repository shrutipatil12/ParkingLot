package com.thoughtworks;

import com.thoughtworks.Exception.ParkingLotIsFullException;
import com.thoughtworks.Exception.VehicleAlreadyParkedException;
import com.thoughtworks.Exception.VehicleNotParkedException;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    private List<Object> parkObject = new ArrayList<>();
    private int capacity;
    private IOwner owner;
    private IOwner securityGuard;

    public ParkingLot(int capacity, IOwner owner) {
        this.capacity = capacity;
        this.owner = owner;
    }

    public ParkingLot(int capacity, IOwner owner, IOwner securityGuard) {
        this.capacity = capacity;
        this.owner = owner;
        this.securityGuard = securityGuard;
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
                securityGuard.informParkingLotIsNowAvailable();
            }
            return object;
        }
        throw new VehicleNotParkedException();
    }

    private void informIfCapacityFull() {
        if (parkObject.size() == capacity) {
            owner.informParkingLotFull();
            securityGuard.informParkingLotFull();
        }
    }
}





