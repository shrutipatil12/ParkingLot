package com.thoughtworks;

import com.thoughtworks.Exception.ParkingLotIsFullException;
import com.thoughtworks.Exception.VehicleNotParkedException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DummyOwner implements Subscriber {
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

class DummySecurity implements Subscriber {
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
        List<Subscriber> subscribers = new ArrayList<>();
        DummyOwner owner = new DummyOwner();
        subscribers.add(owner);
        ParkingLot parkingLot = new ParkingLot(1, subscribers);

        Object vehicle = new Object();
        assertDoesNotThrow(() -> parkingLot.park(vehicle));
    }


    @Test
    void givenParkingLotWithFullCapacity_WhenPark_ThenShouldNotBeAbleToPark() throws Exception {
        List<Subscriber> subscribers = new ArrayList<>();
        DummyOwner owner = new DummyOwner();
        subscribers.add(owner);
        ParkingLot parkingLot = new ParkingLot(1, subscribers);

        Object vehicle = new Object();
        parkingLot.park(vehicle);

        Assertions.assertThrows(ParkingLotIsFullException.class, () -> {
            parkingLot.park(new Object());
        });
    }

    @Test
    void givenParkingLotWithOneCapacity_WhenParkOneVehicleAndUnParkIt_ThenShouldBeAbleToUnParkIt() throws Exception {
        List<Subscriber> subscribers = new ArrayList<>();
        DummyOwner owner = new DummyOwner();
        subscribers.add(owner);
        ParkingLot parkingLot = new ParkingLot(1, subscribers);

        Object vehicle = new Object();
        parkingLot.park(vehicle);

        assertEquals(vehicle, parkingLot.unPark(vehicle));
    }

    @Test
    void givenParkingLotWithOneCapacity_WhenUnParkOneVehicleWithoutPark_ThenShouldNotBeAbleToUnParkIt() {
        List<Subscriber> subscribers = new ArrayList<>();
        DummyOwner owner = new DummyOwner();
        subscribers.add(owner);
        ParkingLot parkingLot = new ParkingLot(1, subscribers);

        Object vehicle = new Object();

        Assertions.assertThrows(VehicleNotParkedException.class, () -> {
            parkingLot.unPark(vehicle);
        });
    }

    @Test
    void givenParkingLotWithTwoCapacity_WhenParkTwoVehicleAndUnPark_ThenShouldBeAbleToUnPark() throws Exception {

        List<Subscriber> subscribers = new ArrayList<>();
        DummyOwner owner = new DummyOwner();
        subscribers.add(owner);
        ParkingLot parkingLot = new ParkingLot(2, subscribers);

        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

        assertEquals(vehicleOne, parkingLot.unPark(vehicleOne));
        assertEquals(vehicleTwo, parkingLot.unPark(vehicleTwo));

    }

    @Test
    void givenParkingLot_WhenGetFull_ThenShouldAbleToInform() throws Exception {

        List<Subscriber> subscribers = new ArrayList<>();
        DummyOwner owner = new DummyOwner();
        subscribers.add(owner);
        ParkingLot parkingLot = new ParkingLot(1, subscribers);

        Object vehicleOne = new Object();
        parkingLot.park(vehicleOne);

        assertEquals(1, owner.parkingLotFull);

    }

    @Test
    void givenParkingLot_WhenGetFull_ThenShouldAbleToInformOneTime() throws Exception {

        List<Subscriber> subscribers = new ArrayList<>();
        DummyOwner owner = new DummyOwner();
        subscribers.add(owner);
        ParkingLot parkingLot = new ParkingLot(2, subscribers);

        Object vehicleOne = new Object();
        parkingLot.park(vehicleOne);

        Object vehicleTwo = new Object();
        parkingLot.park(vehicleTwo);

        assertEquals(1, owner.parkingLotFull);

    }

    @Test
    void givenParkingLot_WhenParkingLotFirstBecomesFullAndThenBecomesAvailable_ThenOwnerShouldGetBothNotifications() throws Exception {
        List<Subscriber> subscribers = new ArrayList<>();
        DummyOwner owner = new DummyOwner();
        subscribers.add(owner);
        ParkingLot parkingLot = new ParkingLot(2, subscribers);

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
        List<Subscriber> subscribers = new ArrayList<>();
        DummyOwner owner = new DummyOwner();
        DummySecurity securityGuard = new DummySecurity();
        subscribers.add(owner);
        subscribers.add(securityGuard);

        ParkingLot parkingLot = new ParkingLot(2, subscribers);
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
