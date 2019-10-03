package com.thoughtworks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ParkingLotTest {

    @Test
    void givenParkingLotWithCapacity_WhenWePark_ThenShouldBeAbleToPark() {
        ParkingLot parkingLot = new ParkingLot(1);
        assertTrue(parkingLot.park(new Object()));
    }
}
