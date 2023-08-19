package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SuperParkingBoyTest {
    
    @Test
    void should_park_to_first_parking_lot_when_park_given_a_super_parking_boy_and_two_parking_lots_and_a_car() {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLots);
        Car car = new Car();

        //when
        ParkingTicket parkingTicket = superParkingBoy.park(car);

        //then
        assertNotNull(parkingTicket);
        assertEquals(9, firstParkingLot.getAvailableCapacity());
        assertEquals(10, secondParkingLot.getAvailableCapacity());
    }

    @Test
    void should_park_to_second_parking_lot_when_park_given_a_super_parking_boy_and_two_parking_lots_with_first_parking_lot_full_and_a_car() {
        //given
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLots);
        firstParkingLot.park(new Car());
        Car car = new Car();

        //when
        ParkingTicket parkingTicket = superParkingBoy.park(car);

        //then
        assertNotNull(parkingTicket);
        assertEquals(0, firstParkingLot.getAvailableCapacity());
        assertEquals(9, secondParkingLot.getAvailableCapacity());
    }

    @Test
    void should_park_to_first_parking_lot_when_park_given_a_super_parking_boy_and_two_parking_lots_with_first_parking_lot_having_larger_available_position_rate_and_a_car() {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLots);
        for (int i = 0; i < 3; i++) {
            firstParkingLot.park(new Car());
        }
        for (int i = 0; i < 5; i++) {
            secondParkingLot.park(new Car());
        }
        Car toParkCar = new Car();

        //when
        ParkingTicket parkingTicket = superParkingBoy.park(toParkCar);

        //then
        assertNotNull(parkingTicket);
        assertEquals(6, firstParkingLot.getAvailableCapacity());
        assertEquals(5, secondParkingLot.getAvailableCapacity());
    }

    @Test
    void should_park_to_second_parking_lot_when_park_given_a_super_parking_boy_and_two_parking_lots_with_second_parking_lot_having_larger_available_position_rate_and_a_car() {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot(20);
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLots);
        for (int i = 0; i < 6; i++) {
            firstParkingLot.park(new Car());
        }
        for (int i = 0; i < 9; i++) {
            secondParkingLot.park(new Car());
        }
        Car toParkCar = new Car();

        //when
        ParkingTicket parkingTicket = superParkingBoy.park(toParkCar);

        //then
        assertNotNull(parkingTicket);
        assertEquals(4, firstParkingLot.getAvailableCapacity());
        assertEquals(10, secondParkingLot.getAvailableCapacity());
    }



    @Test
    void should_park_to_first_parking_lot_when_park_given_a_super_parking_boy_and_two_parking_lots_having_equal_available_position_rate_and_a_car() {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot(20);
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLots);
        for (int i = 0; i < 6; i++) {
            firstParkingLot.park(new Car());
        }
        for (int i = 0; i < 12; i++) {
            secondParkingLot.park(new Car());
        }
        Car toParkCar = new Car();

        //when
        ParkingTicket parkingTicket = superParkingBoy.park(toParkCar);

        //then
        assertNotNull(parkingTicket);
        assertEquals(3, firstParkingLot.getAvailableCapacity());
        assertEquals(8, secondParkingLot.getAvailableCapacity());
    }
}
