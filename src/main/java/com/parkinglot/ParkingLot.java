package com.parkinglot;

import com.parkinglot.exception.UnrecognizedTicketException;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private static final int DEFAULT_CAPACITY = 10;
    private final int capacity;
    private Car car;
    private final Map<ParkingTicket, Car> parkingTicketsAndCarsMap = new HashMap<>();

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public ParkingLot() {
        this(DEFAULT_CAPACITY);
    }

    public ParkingTicket park(Car car) {
        if (parkingTicketsAndCarsMap.size() == capacity) {
            return null;
        }
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingTicketsAndCarsMap.put(parkingTicket, car);
        return parkingTicket;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        if (parkingTicketsAndCarsMap.get(parkingTicket) == null) {
            throw new UnrecognizedTicketException();
        }
        return parkingTicketsAndCarsMap.remove(parkingTicket);
    }
}
