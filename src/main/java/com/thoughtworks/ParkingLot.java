package com.thoughtworks;

public class ParkingLot {

    private int capacity;

    public ParkingLot(int capacity) {

        this.capacity = capacity;
    }

    public boolean park(Object object) {
        if (capacity == 0) {
            return false;
        }
        return true;
    }


}



