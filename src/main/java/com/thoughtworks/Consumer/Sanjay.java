package com.thoughtworks.Consumer;

import com.thoughtworks.Exception.ParkingLotIsFullException;
import com.thoughtworks.Exception.VehicleAlreadyParkedException;
import com.thoughtworks.ParkingLot;

public class Sanjay {

    public static void park(ParkingLot parkingLotOne, ParkingLot parkingLotTwo) throws ParkingLotIsFullException, VehicleAlreadyParkedException {

        try {
            System.out.println("park to parkingLotOne");
            final Object A = new Object();

            parkingLotOne.park(A);
            parkingLotOne.park(A);

        } catch (ParkingLotIsFullException e) {
            System.out.println("exception  " + e.getMessage());
        }

        try {
            System.out.println("park to parkingLotOne");
            final Object A = new Object();
            final Object B = new Object();
            final Object C = new Object();


            parkingLotOne.park(A);
            parkingLotOne.park(B);
            parkingLotOne.park(C);
        } catch (ParkingLotIsFullException e) {
            System.out.println("exception  " + e.getMessage());

        }
        System.out.println("create Car C object");

        final Object C = new Object();
        System.out.println("Created ");

        parkingLotTwo.park(C);
        System.out.println("Added car c in two " + C);

    }

    public static void main(String[] args) throws ParkingLotIsFullException, VehicleAlreadyParkedException {
//        Owner owner = new Owner();
//        park(new ParkingLot(2, owner), new ParkingLot(3, owner));
    }
}
