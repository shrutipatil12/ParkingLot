package com.thoughtworks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {

    @Test
    void givenParkingLotWithOneCapacity_WhenWePark_ThenShouldBeAbleToPark() throws ParkingLotException, VehicleAlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(1);

        Object object1 = new Object();
        assertTrue(parkingLot.park(object1));
    }


    @Test
    void givenParkingLotWithFullcapacity_WhenPark_ThenShouldNotBeAbleToPark() throws ParkingLotException, VehicleAlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(2);

        Object vehicle = new Object();
        parkingLot.park(vehicle);

        Assertions.assertThrows(VehicleAlreadyParkedException.class, () -> {
            parkingLot.park(vehicle);
        });
    }

    @Test
    void givenParkingLotWithOneCapacity_WhenParkOneVehicleAndUnParkIt_ThenShouldBeAbleToUnParkIt() throws ParkingLotException, VehicleNotParkedException, VehicleAlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(1);

        Object vehicle = new Object();
        parkingLot.park(vehicle);

        assertEquals(vehicle, parkingLot.unPark(vehicle));
    }

    @Test
    void givenParkingLotWithOneCapacity_WhenUnParkOneVehicleWithoutPark_ThenShouldNotBeAbleToUnParkIt() throws VehicleNotParkedException, ParkingLotException {
        ParkingLot parkingLot = new ParkingLot(1);

        Object vehicle = new Object();

        Assertions.assertThrows(VehicleNotParkedException.class, () -> {
            parkingLot.unPark(vehicle);
        });
    }

    @Test
    void givenParkingLotWithTwoCapacity_WhenParkTwoVehicleAndUnParkSameVehicleTwoTimes_ThenShouldBeAbleToUnParkVehicle() throws ParkingLotException, VehicleNotParkedException, VehicleAlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(2);

        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

        assertNotEquals(vehicleTwo, parkingLot.unPark(vehicleOne));
        assertEquals(vehicleTwo, parkingLot.unPark(vehicleTwo));

    }


}
