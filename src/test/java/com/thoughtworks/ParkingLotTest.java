package com.thoughtworks;

import com.thoughtworks.Exception.ParkingLotIsFullException;
import com.thoughtworks.Exception.VehicleNotParkedException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DummyOwner implements IOwner {
    int parkingLotAvailable = 0;
    int parkingLotFull = 0;


    @Override
    public void informParkingLotIsNowAvailable() {
        parkingLotAvailable++;

    }

    @Override
    public void informParkingLotFull() {
        parkingLotFull++;
    }
}

class DummySecurity implements IOwner {
    int parkingLotAvailable = 0;
    int parkingLotFull = 0;


    @Override
    public void informParkingLotIsNowAvailable() {
        parkingLotAvailable++;

    }

    @Override
    public void informParkingLotFull() {
        parkingLotFull++;
    }
}


class ParkingLotTest {
    @Test
    void givenParkingLotWithOneCapacity_WhenWePark_ThenShouldBeAbleToPark() {

        DummyOwner owner = new DummyOwner();
        DummySecurity securityGuard = new DummySecurity();
        ParkingLot parkingLot = new ParkingLot(1, owner, securityGuard);

        Object object1 = new Object();
        assertDoesNotThrow(() -> parkingLot.park(object1));
    }


    @Test
    void givenParkingLotWithFullCapacity_WhenPark_ThenShouldNotBeAbleToPark() throws Exception {

        DummyOwner owner = new DummyOwner();
        DummySecurity securityGuard = new DummySecurity();
        ParkingLot parkingLot = new ParkingLot(1, owner, securityGuard);

        Object vehicle = new Object();
        parkingLot.park(vehicle);

        Assertions.assertThrows(ParkingLotIsFullException.class, () -> {
            parkingLot.park(new Object());
        });
    }

    @Test
    void givenParkingLotWithOneCapacity_WhenParkOneVehicleAndUnParkIt_ThenShouldBeAbleToUnParkIt() throws Exception {
        DummyOwner owner = new DummyOwner();
        DummySecurity securityGuard = new DummySecurity();
        ParkingLot parkingLot = new ParkingLot(1, owner, securityGuard);

        Object vehicle = new Object();
        parkingLot.park(vehicle);

        assertEquals(vehicle, parkingLot.unPark(vehicle));
    }

    @Test
    void givenParkingLotWithOneCapacity_WhenUnParkOneVehicleWithoutPark_ThenShouldNotBeAbleToUnParkIt() {

        DummyOwner owner = new DummyOwner();
        DummySecurity securityGuard = new DummySecurity();
        ParkingLot parkingLot = new ParkingLot(1, owner, securityGuard);

        Object vehicle = new Object();

        Assertions.assertThrows(VehicleNotParkedException.class, () -> {
            parkingLot.unPark(vehicle);
        });
    }

    @Test
    void givenParkingLotWithTwoCapacity_WhenParkTwoVehicleAndUnPark_ThenShouldBeAbleToUnPark() throws Exception {

        DummyOwner owner = new DummyOwner();
        DummySecurity securityGuard = new DummySecurity();
        ParkingLot parkingLot = new ParkingLot(2, owner, securityGuard);

        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

        assertEquals(vehicleOne, parkingLot.unPark(vehicleOne));
        assertEquals(vehicleTwo, parkingLot.unPark(vehicleTwo));

    }

    @Test
    void givenParkingLot_WhenGetFull_ThenShouldAbleToInform() throws Exception {

        DummyOwner owner = new DummyOwner();
        DummySecurity securityGuard = new DummySecurity();
        ParkingLot parkingLot = new ParkingLot(1, owner, securityGuard);

        Object vehicleOne = new Object();
        parkingLot.park(vehicleOne);

        assertEquals(1, owner.parkingLotFull);

    }

    @Test
    void givenParkingLot_WhenGetFull_ThenShouldAbleToInformOneTime() throws Exception {

        DummyOwner owner = new DummyOwner();
        DummySecurity securityGuard = new DummySecurity();
        ParkingLot parkingLot = new ParkingLot(2, owner, securityGuard);

        Object vehicleOne = new Object();
        parkingLot.park(vehicleOne);

        Object vehicleTwo = new Object();
        parkingLot.park(vehicleTwo);

        assertEquals(1, owner.parkingLotFull);

    }

    @Test
    void givenParkingLot_WhenParkingLotFirstBecomesFullAndThenBecomesAvailable_ThenOwnerShouldGetBothNotifications() throws Exception {

        DummyOwner owner = new DummyOwner();
        DummySecurity securityGuard = new DummySecurity();
        ParkingLot parkingLot = new ParkingLot(2, owner, securityGuard);

        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

        assertEquals(1, owner.parkingLotFull);
        parkingLot.unPark(vehicleOne);
        assertEquals(1, owner.parkingLotAvailable);

    }

    @Test
    void givenParkingLot_WhenParkingLotFirstBecomesFullAndThenBecomesAvailable_ThenOwnerAndSecurityGuardShouldGetBothNotifications() throws Exception {
        DummyOwner owner = new DummyOwner();
        DummySecurity securityGuard = new DummySecurity();

        ParkingLot parkingLot = new ParkingLot(2, owner, securityGuard);
        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();

        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);
        assertEquals(1, owner.parkingLotFull);
        assertEquals(1, securityGuard.parkingLotFull);

        parkingLot.unPark(vehicleOne);

        assertEquals(1, owner.parkingLotAvailable);
        assertEquals(1, securityGuard.parkingLotAvailable);

    }
}
