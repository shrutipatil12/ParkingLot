package com.thoughtworks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ParkingLotTest {

    @Test
    void givenParkingLot_WhenWePark_ThenShouldBeAbleToPark() {
        ParkingLot parkingLot = new ParkingLot();
        assertTrue(parkingLot.park(new Object()));
    }
}
