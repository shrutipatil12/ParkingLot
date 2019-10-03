package com.thoughtworks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParkingLotTest {

    @Test
    void givenParkingLotWithTwoCapacity_WhenWePark_ThenShouldBeAbleToPark() {
        ParkingLot parkingLot = new ParkingLot(2);
        assertTrue(parkingLot.park(new Object()));
    }

    @Test
    void givenParkingLotWithOneCapacity_WhenWePark_ThenShouldBeAbleToPark() {
        ParkingLot parkingLot = new ParkingLot(1);
        assertTrue(parkingLot.park(new Object()));
    }

    @Test
    void givenParkingLotWithZeroCapacity_WhenWePark_ThenShouldNotBeAbleToPark() {
        ParkingLot parkingLot = new ParkingLot(0);
        assertFalse(parkingLot.park(new Object()));
    }
}
