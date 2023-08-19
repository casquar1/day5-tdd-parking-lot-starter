package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SmartParkingBoyTest {
    
    @Test
    void should_park_to_first_parking_lot_when_park_given_a_smart_parking_boy_and_two_parking_lots_and_a_car() {
    //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car();
     
     //when
        ParkingTicket parkingTicket = smartParkingBoy.park(car);
     
     //then
        assertNotNull(parkingTicket);
        assertEquals(9, firstParkingLot.getAvailableCapacity());
        assertEquals(10, secondParkingLot.getAvailableCapacity());
    }

    @Test
    void should_park_to_second_parking_lot_when_park_given_a_smart_parking_boy_and_two_parking_lots_with_first_parking_lot_full_and_a_car() {
    //given
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        firstParkingLot.park(new Car());
        Car car = new Car();

     //when
        ParkingTicket parkingTicket = smartParkingBoy.park(car);

     //then
        assertNotNull(parkingTicket);
        assertEquals(0, firstParkingLot.getAvailableCapacity());
        assertEquals(9, secondParkingLot.getAvailableCapacity());
    }

    @Test
    void should_park_to_first_parking_lot_when_park_given_a_smart_parking_boy_and_two_parking_lots_with_first_parking_lot_having_more_empty_positions_and_a_car() {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            cars.add(new Car());
        }
        for (Car car: cars) {
            secondParkingLot.park(car);
        }
        Car toParkCar = new Car();

        //when
        ParkingTicket parkingTicket = smartParkingBoy.park(toParkCar);

        //then
        assertNotNull(parkingTicket);
        assertEquals(9, firstParkingLot.getAvailableCapacity());
        assertEquals(7, secondParkingLot.getAvailableCapacity());
    }

    @Test
    void should_park_to_second_parking_lot_when_park_given_a_smart_parking_boy_and_two_parking_lots_with_second_parking_lot_having_more_empty_positions_and_a_car() {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            cars.add(new Car());
        }
        for (Car car: cars) {
            firstParkingLot.park(car);
        }
        Car toParkCar = new Car();

        //when
        ParkingTicket parkingTicket = smartParkingBoy.park(toParkCar);

        //then
        assertNotNull(parkingTicket);
        assertEquals(5, firstParkingLot.getAvailableCapacity());
        assertEquals(9, secondParkingLot.getAvailableCapacity());
    }

    @Test
    void should_park_to_first_parking_lot_when_park_given_a_smart_parking_boy_and_two_parking_lots_both_having_equal_empty_positions_and_a_car() {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            cars.add(new Car());
        }
        for (Car car: cars) {
            firstParkingLot.park(car);
            secondParkingLot.park(car);
        }
        Car toParkCar = new Car();

        //when
        ParkingTicket parkingTicket = smartParkingBoy.park(toParkCar);

        //then
        assertNotNull(parkingTicket);
        assertEquals(2, firstParkingLot.getAvailableCapacity());
        assertEquals(3, secondParkingLot.getAvailableCapacity());
    }
}
