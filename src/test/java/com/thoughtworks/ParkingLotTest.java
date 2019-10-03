package com.thoughtworks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParkingLotTest {

    @Test
    void givenParkingLotWithZeroCapacity_WhenWePark_ThenShouldNotBeAbleToPark() {
        ParkingLot parkingLot = new ParkingLot(1);
        Object object1 = new Object();
        assertTrue(parkingLot.park(object1));
    }

    @Test
    void givenParkingLotWithSameObject_WhenWePark_ThenShouldNotBeAbleToPark() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1);
        Object object1 = new Object();
        assertTrue(parkingLot.park(object1));
        assertFalse(parkingLot.park(object1));
    }

}
