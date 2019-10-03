package com.thoughtworks.Consumer;

import com.thoughtworks.ParkingLot;
import com.thoughtworks.ParkingLotException;

public class Sanjay {

    public static void park(ParkingLot parkingLotOne, ParkingLot parkingLotTwo) throws ParkingLotException {

        try {
            System.out.println("park to parkingLotOne");
            final Object A = new Object();

            parkingLotOne.park(A);
            parkingLotOne.park(A);

        } catch (ParkingLotException e) {
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
        } catch (ParkingLotException e) {
            System.out.println("exception  " + e.getMessage());

        }
        System.out.println("create Car C object");

        final Object C = new Object();
        System.out.println("Created ");

        parkingLotTwo.park(C);
        System.out.println("Added car c in two " + C);

    }

    public static void main(String[] args) throws ParkingLotException {

        park(new ParkingLot(2), new ParkingLot(3));
    }
}
