package com.thoughtworks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DummyOwner extends Owner {
    boolean wasCalled = false;
    int parkingLotAvailable = 0;
    int parkingLotFull = 0;


    @Override
    public void informParkingLotIsNowAvailable() {
        wasCalled = true;
        parkingLotAvailable++;

    }

    @Override
    public void informParkingLotFull() {
        wasCalled = true;
        parkingLotFull++;
    }
}


class ParkingLotTest {
    @Test
    void givenParkingLotWithOneCapacity_WhenWePark_ThenShouldBeAbleToPark() throws VehicleAlreadyParkedException, ParkingLotIsFullException {

        Owner owner = new Owner();
        ParkingLot parkingLot = new ParkingLot(2, owner);

        Object object1 = new Object();
        assertTrue(parkingLot.park(object1));
    }


    @Test
    void givenParkingLotWithFullCapacity_WhenPark_ThenShouldNotBeAbleToPark() throws ParkingLotIsFullException, VehicleAlreadyParkedException {

        Owner owner = new Owner();
        ParkingLot parkingLot = new ParkingLot(2, owner);

        Object vehicle = new Object();
        parkingLot.park(vehicle);

        Assertions.assertThrows(VehicleAlreadyParkedException.class, () -> {
            parkingLot.park(vehicle);
        });
    }

    @Test
    void givenParkingLotWithOneCapacity_WhenParkOneVehicleAndUnParkIt_ThenShouldBeAbleToUnParkIt() throws ParkingLotIsFullException, VehicleNotParkedException, VehicleAlreadyParkedException {
        Owner owner = new Owner();
        ParkingLot parkingLot = new ParkingLot(1, owner);

        Object vehicle = new Object();
        parkingLot.park(vehicle);

        assertEquals(vehicle, parkingLot.unPark(vehicle));
    }

    @Test
    void givenParkingLotWithOneCapacity_WhenUnParkOneVehicleWithoutPark_ThenShouldNotBeAbleToUnParkIt() {

        Owner owner = new Owner();
        ParkingLot parkingLot = new ParkingLot(1, owner);

        Object vehicle = new Object();

        Assertions.assertThrows(VehicleNotParkedException.class, () -> {
            parkingLot.unPark(vehicle);
        });
    }

    @Test
    void givenParkingLotWithTwoCapacity_WhenParkTwoVehicleAndUnParkSameVehicleTwoTimes_ThenShouldNotAbleToUnParkVehicle() throws ParkingLotIsFullException, VehicleNotParkedException, VehicleAlreadyParkedException {

        Owner owner = new Owner();
        ParkingLot parkingLot = new ParkingLot(3, owner);

        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

        assertNotEquals(vehicleTwo, parkingLot.unPark(vehicleOne));
        assertEquals(vehicleTwo, parkingLot.unPark(vehicleTwo));

    }

    @Test
    void givenParkingLot_WhenGetFull_ThenShouldAbleToInform() throws ParkingLotIsFullException, VehicleAlreadyParkedException {
        DummyOwner owner = new DummyOwner();
        ParkingLot parkingLot = new ParkingLot(1, owner);

        Object vehicleOne = new Object();
        parkingLot.park(vehicleOne);

        assertTrue(owner.wasCalled);

    }

    @Test
    void givenParkingLot_WhenGetFull_ThenShouldAbleToInformOneTime() throws ParkingLotIsFullException, VehicleAlreadyParkedException {
        DummyOwner owner = new DummyOwner();
        ParkingLot parkingLot = new ParkingLot(2, owner);

        Object vehicleOne = new Object();
        parkingLot.park(vehicleOne);

        Object vehicleTwo = new Object();
        parkingLot.park(vehicleTwo);

        assertEquals(1, owner.parkingLotFull);

    }

    @Test
    void givenParkingLotWithTwoCapacity_WhenParkedAndUnParked_ThenShouldReturnCountOfNotification() throws ParkingLotIsFullException, VehicleAlreadyParkedException, VehicleNotParkedException {
        DummyOwner owner = new DummyOwner();
        ParkingLot parkingLot = new ParkingLot(2, owner);
        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();

        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);
        assertEquals(1, owner.parkingLotFull);

        parkingLot.unPark(vehicleOne);

        assertEquals(1, owner.parkingLotAvailable);

    }
}
