package com.parkinglot;

import com.parkinglot.exception.UnrecognizedTicketException;
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
    
    @Test
    void should_return_right_car_when_fetch_given_a_smart_parking_boy_two_parking_lots_both_with_parked_car_and_two_parking_tickets() {
    //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car firstCar = new Car();
        Car secondCar = new Car();
        ParkingTicket firstParkingTicket = smartParkingBoy.park(firstCar);
        ParkingTicket secondParkingTicket = smartParkingBoy.park(secondCar);
     
     //when
        Car fetchFirstCar = smartParkingBoy.fetch(firstParkingTicket);
        Car fetchSecondCar = smartParkingBoy.fetch(secondParkingTicket);
     
     //then
        assertEquals(firstCar, fetchFirstCar);
        assertEquals(secondCar, fetchSecondCar);
    }

    @Test
    void should_return_unrecognizedTicketException_when_fetch_given_a_smart_parking_boy_two_parking_lots_and_an_unrecognized_ticket() {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car();
        smartParkingBoy.park(car);
        ParkingTicket parkingTicket = new ParkingTicket();

        //when
        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class, () -> {
            smartParkingBoy.fetch(parkingTicket);
        });

        //then
        assertEquals("Unrecognized parking ticket.", unrecognizedTicketException.getMessage());
    }
}
