package com.thoughtworks;

import java.util.ArrayList;

public class ParkingLot {

    ArrayList<Object> parkObject = new ArrayList<>();
    private int capacity;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public boolean park(Object object) {
        parkObject.add(object);
        if (parkObject.size() <= capacity) {
            return true;
        }
        if (!parkObject.contains(object)) {
            return false;
        }
        return false;
    }

}



