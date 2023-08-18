package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParkingLotTest {

    @Test
    void should_return_parking_ticket_when_park_given_parking_lot_and_car() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();

        //when
        ParkingTicket parkingTicket = parkingLot.park(car);

        //then
        assertNotNull(parkingTicket);
    }

    @Test
    void should_return_car_when_fetch_given_parking_lot_and_parking_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingTicket parkingTicket = parkingLot.park(car);

        //when
        Car fetchedCar = parkingLot.fetch(parkingTicket);

        //then
        assertEquals(car, fetchedCar);
    }

    @Test
    void should_return_right_car_for_each_ticket_when_fetch_given_parking_lot_with_two_cars() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car firstCar = new Car();
        Car secondCcar = new Car();
        ParkingTicket parkingTicket1 = parkingLot.park(firstCar);
        ParkingTicket parkingTicket2 = parkingLot.park(secondCcar);

        //when
        Car fetchFirstCar = parkingLot.fetch(parkingTicket1);
        Car fetchSecondCar = parkingLot.fetch(parkingTicket2);

        //then
        assertEquals(firstCar, fetchFirstCar);
        assertEquals(secondCcar, fetchSecondCar);
    }
}
