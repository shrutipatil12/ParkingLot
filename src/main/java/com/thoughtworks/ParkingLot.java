package com.thoughtworks;

import com.thoughtworks.Exception.ParkingLotIsFullException;
import com.thoughtworks.Exception.VehicleAlreadyParkedException;
import com.thoughtworks.Exception.VehicleNotParkedException;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    private List<Object> parkedObjects = new ArrayList<>();
    private List<Subscriber> subscriber;
    private int capacity;


    public ParkingLot(int capacity, List<Subscriber> subscribers) {
        this.capacity = capacity;
        this.subscriber = subscribers;
    }

    public void park(Object object) throws ParkingLotIsFullException, VehicleAlreadyParkedException {
        if (isAlreadyParked(object)) {
            throw new VehicleAlreadyParkedException();
        }

        if (isSpaceAvailable()) {
            parkedObjects.add(object);
            if (isCapacityFull()) {
                for (Subscriber subscriber : this.subscriber) {
                    subscriber.informParkingLotFull();
                }
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
            if (isParkingLotAvailableAgain()) {
                for (Subscriber subscriber : subscriber) {
                    subscriber.informParkingLotIsNowAvailable();
                }
            }
            return object;
        }
        throw new VehicleNotParkedException();
    }

    private boolean isParkingLotAvailableAgain() {
        return parkedObjects.size() == capacity - 1;
    }

    private boolean isCapacityFull() {
        return parkedObjects.size() == capacity;
    }

}
