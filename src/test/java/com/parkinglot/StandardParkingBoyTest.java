package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StandardParkingBoyTest {

    @Test
    void should_park_to_first_parking_lot_when_park_given_a_standard_parking_boy_and_two_parking_lots_and_a_car() {
    //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLots);
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
        Car car = new Car();
     
     //when
        ParkingTicket parkingTicket = standardParkingBoy.park(car);
     
     //then
        assertNotNull(parkingTicket);
        assertFalse(firstParkingLot.hasAvailableCapacity());
        assertTrue(secondParkingLot.hasAvailableCapacity());
    }
    
    @Test
    void should_return_right_car_when_fetch_car_given_a_standard_parking_boy_two_parking_lots_both_with_parked_car_and_two_parking_tickets() {
    //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLots);
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
}
