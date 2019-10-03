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

    public boolean park(Object object) throws Exception {

        if (parkObject.size() <= capacity) {
            if (parkObject.contains(object)) {
                throw new Exception("Already contains vehical");
            }
            parkObject.add(object);
            return true;
        }
        throw new Exception("Already Full");
    }

}





