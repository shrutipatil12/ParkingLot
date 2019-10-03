package com.thoughtworks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ParkingLotTest {

    @Test
    void givenParkingLotWithZeroCapacity_WhenWePark_ThenShouldNotBeAbleToPark() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1);
        Object object1 = new Object();
        assertTrue(parkingLot.park(object1));
    }


    @Test
    void givenParkingLotWithSameObject_WhenWePark_ThenShouldThrowException() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1);
        Object vehical = new Object();

        assertTrue(parkingLot.park(vehical));
        Exception msg = Assertions.assertThrows(Exception.class, () -> {
            parkingLot.park(vehical);
        });
    }

    @Test
    void givenParkingLotWithFullcapacity_WhenPark_ThenShouldNotBeAbleToPark() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1);
        Object vehical = new Object();

        assertTrue(parkingLot.park(vehical));
        Exception msg = Assertions.assertThrows(Exception.class, () -> {
            parkingLot.park(vehical);
        });
    }

}
