package com.thoughtworks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ParkingLotTest {

    @Test
    void givenParkingLotWithOneCapacity_WhenWePark_ThenShouldBeAbleToPark() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot(1);

        Object object1 = new Object();
        assertTrue(parkingLot.park(object1));
    }


    @Test
    void givenParkingLotWithSameObject_WhenWePark_ThenShouldThrowException() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot(1);
        Object vehicle = new Object();

        assertTrue(parkingLot.park(vehicle));
        Assertions.assertThrows(ParkingLotException.class, () -> {
            parkingLot.park(vehicle);
        });
    }

    @Test
    void givenParkingLotWithFullcapacity_WhenPark_ThenShouldNotBeAbleToPark() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot(1);

        Object vehicle = new Object();
        assertTrue(parkingLot.park(vehicle));

        Assertions.assertThrows(ParkingLotException.class, () -> {
            parkingLot.park(vehicle);
        });
    }

    @Test
    void givenParkingLotWithOneCapacity_WhenParkOneVehicleAndUnParkIt_ThenShouldBeAbleToUnParkIt() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot(1);

        Object vehicle = new Object();
        parkingLot.park(vehicle);

        assertTrue(parkingLot.unPark(vehicle));
    }

}
