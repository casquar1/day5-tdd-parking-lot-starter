package com.parkinglot;

import com.parkinglot.exception.NoAvailablePositionException;
import com.parkinglot.exception.UnrecognizedTicketException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StandardParkingBoyTest {
    ParkingLot firstParkingLot = new ParkingLot();
    ParkingLot secondParkingLot = new ParkingLot();
    List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
    StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLots);
    @Test
    void should_park_to_first_parking_lot_when_park_given_a_standard_parking_boy_and_two_parking_lots_and_a_car() {
    //given
        Car car = new Car();

     //when
        ParkingTicket parkingTicket = standardParkingBoy.park(car);

     //then
        assertNotNull(parkingTicket);
        assertEquals(9, firstParkingLot.getAvailableCapacity());
        assertEquals(10, secondParkingLot.getAvailableCapacity());
    }
    
    @Test
    void should_park_to_second_parking_lot_when_park_given_a_standard_parking_boy_and_two_parking_lots_with_first_parking_lot_full_and_a_car() {
    //given
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLots);
        firstParkingLot.park(new Car());
        Car car = new Car();
     
     //when
        ParkingTicket parkingTicket = standardParkingBoy.park(car);
     
     //then
        assertNotNull(parkingTicket);
        assertEquals(0, firstParkingLot.getAvailableCapacity());
        assertEquals(9, secondParkingLot.getAvailableCapacity());
    }
    
    @Test
    void should_return_right_car_when_fetch_given_a_standard_parking_boy_two_parking_lots_both_with_parked_car_and_two_parking_tickets() {
    //given
        Car firstCar = new Car();
        Car secondCar = new Car();
        ParkingTicket firstParkingTicket = standardParkingBoy.park(firstCar);
        ParkingTicket secondParkingTicket = standardParkingBoy.park(secondCar);
     
     //when
        Car fetchFirstCar = standardParkingBoy.fetch(firstParkingTicket);
        Car fetchSecondCar = standardParkingBoy.fetch(secondParkingTicket);
     
     //then
        assertEquals(firstCar, fetchFirstCar);
        assertEquals(secondCar, fetchSecondCar);
    }

    @Test
    void should_return_unrecognizedTicketException_when_fetch_given_a_standard_parking_boy_two_parking_lots_and_an_unrecognized_ticket() {
    //given
        Car car = new Car();
        standardParkingBoy.park(car);
        ParkingTicket parkingTicket = new ParkingTicket();

     //when
        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class, () -> {
            standardParkingBoy.fetch(parkingTicket);
        });

     //then
        assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }

    @Test
    void should_return_unrecognizedTicketException_when_fetch_given_a_standard_parking_boy_two_parking_lots_and_a_used_ticket() {
    //given
        Car car = new Car();
        ParkingTicket parkingTicket = standardParkingBoy.park(car);
        standardParkingBoy.fetch(parkingTicket);

     //when
        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class, () -> {
            standardParkingBoy.fetch(parkingTicket);
        });

     //then
        assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }
    
    @Test
    void should_return_noAvailablePositionException_when_park_given_a_standard_parking_boy_two_parking_lots_both_full() {
    //given
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLots);
        Car car = new Car();
        Car firstParkedCar = new Car();
        Car secondParkedCar = new Car();
        standardParkingBoy.park(firstParkedCar);
        standardParkingBoy.park(secondParkedCar);
     
     //when
        NoAvailablePositionException noAvailablePositionException = assertThrows(NoAvailablePositionException.class, () -> {
            standardParkingBoy.park(car);
        });
     
     //then
        assertEquals("No available position.", noAvailablePositionException.getMessage());
    }
}
