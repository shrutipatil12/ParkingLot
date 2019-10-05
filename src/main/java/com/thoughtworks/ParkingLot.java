package com.thoughtworks;

import com.thoughtworks.Exception.ParkingLotIsFullException;
import com.thoughtworks.Exception.VehicleAlreadyParkedException;
import com.thoughtworks.Exception.VehicleNotParkedException;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    private List<Object> parkedObjects = new ArrayList<>();
    private int capacity;
    private IOwner owner;
    private IOwner securityGuard;

    // TODO - list -> 0 in that list. 1. 2.
    public ParkingLot(int capacity, IOwner owner, IOwner securityGuard) {
        this.capacity = capacity;
        this.owner = owner;
        this.securityGuard = securityGuard;
    }

    public void park(Object object) throws ParkingLotIsFullException, VehicleAlreadyParkedException {
        if (isAlreadyParked(object)) {
            throw new VehicleAlreadyParkedException();
        }

        if (isSpaceAvailable()) {
            parkedObjects.add(object);
            if (isCapacityFull()) {
                owner.informParkingLotFull();
                securityGuard.informParkingLotFull();
            }
        }
        if (parkedObjects.size() > capacity) {
            throw new ParkingLotIsFullException();
        }
    }


    private boolean isAlreadyParked(Object object) {
        return parkedObjects.contains(object);
    }

    private boolean isSpaceAvailable() {
        return parkedObjects.size() <= capacity;
    }

    public Object unPark(Object object) throws VehicleNotParkedException {
        if (isAlreadyParked(object)) {
            parkedObjects.remove(object);
            if (parkedObjects.size() == capacity - 1) {

                owner.informParkingLotIsNowAvailable();
                securityGuard.informParkingLotIsNowAvailable();

            }
            return object;
        }
        throw new VehicleNotParkedException();
    }

    public boolean isCapacityFull() {
        return parkedObjects.size() == capacity;
    }

}





