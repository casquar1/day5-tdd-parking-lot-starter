package com.parkinglot;

import com.parkinglot.exception.NoAvailablePositionException;
import com.parkinglot.exception.UnrecognizedTicketException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

    ParkingLot parkingLot = new ParkingLot();

    @Test
    void should_return_parking_ticket_when_park_given_parking_lot_and_car() {
        //given
        Car car = new Car();

        //when
        ParkingTicket parkingTicket = parkingLot.park(car);

        //then
        assertNotNull(parkingTicket);
    }

    @Test
    void should_return_car_when_fetch_given_parking_lot_and_parking_ticket() {
        //given
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
        Car firstCar = new Car();
        Car secondCar = new Car();
        ParkingTicket firstParkingTicket = parkingLot.park(firstCar);
        ParkingTicket secondparkingTicket = parkingLot.park(secondCar);

        //when
        Car fetchFirstCar = parkingLot.fetch(firstParkingTicket);
        Car fetchSecondCar = parkingLot.fetch(secondparkingTicket);

        //then
        assertEquals(firstCar, fetchFirstCar);
        assertEquals(secondCar, fetchSecondCar);
    }

    @Test
    void should_return_unrecognizedTicketException_when_fetch_given_parking_lot_and_wrong_ticket() {
        //given
        Car parkedCar = new Car();
        parkingLot.park(parkedCar);
        ParkingTicket wrongParkingTicket = new ParkingTicket();

        //when
        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class, () -> {
            parkingLot.fetch(wrongParkingTicket);
        });

        //then
        assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }

    @Test
    void should_return_unrecognizedTicketException_when_fetch_given_parking_lot_and_used_ticket() {
        //given
        Car car = new Car();
        ParkingTicket parkingTicket = parkingLot.park(car);
        parkingLot.fetch(parkingTicket);

        //when
        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class, () -> {
            parkingLot.fetch(parkingTicket);
        });

        //then
        assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }

    @Test
    void should_return_noAvailablePositionException_when_park_given_parking_lot_without_any_position() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        Car parkedCar = new Car();
        parkingLot.park(parkedCar);

        //when
        NoAvailablePositionException noAvailablePositionException = assertThrows(NoAvailablePositionException.class, () -> {
            parkingLot.park(car);
        });

        //then
        assertEquals("No available position.", noAvailablePositionException.getMessage());
    }
}
