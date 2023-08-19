package com.parkinglot;

import com.parkinglot.exception.NoAvailablePositionException;
import com.parkinglot.exception.UnrecognizedTicketException;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private static final int DEFAULT_CAPACITY = 10;
    private final int capacity;
    private final Map<ParkingTicket, Car> parkingTicketsAndCarsMap = new HashMap<>();

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public ParkingLot() {
        this(DEFAULT_CAPACITY);
    }

    public ParkingTicket park(Car car) {
        if (isFull()) {
            throw new NoAvailablePositionException();
        }
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingTicketsAndCarsMap.put(parkingTicket, car);
        return parkingTicket;
    }

    public boolean isFull() {
        return parkingTicketsAndCarsMap.size() == capacity;
    }

    public boolean hasParkedCars() {
        return !parkingTicketsAndCarsMap.isEmpty();
    }

    public Car fetch(ParkingTicket parkingTicket) {
        if (!parkingTicketsAndCarsMap.containsKey(parkingTicket)) {
            throw new UnrecognizedTicketException();
        }
        return parkingTicketsAndCarsMap.remove(parkingTicket);
    }

    public int getAvailableCapacity() {
        return capacity - parkingTicketsAndCarsMap.size();
    }

    public boolean hasAvailableCapacity() {
        return !isFull();
    }

    public double getAvailablePositionRate() {
        return (double) getAvailableCapacity() / capacity;
    }
}
