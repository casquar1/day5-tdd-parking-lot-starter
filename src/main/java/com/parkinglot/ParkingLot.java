package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private Car car;
    private final Map<ParkingTicket, Car> parkingTicketsAndCarsMap = new HashMap<>();

    public ParkingTicket park(Car car) {
        this.car = car;
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingTicketsAndCarsMap.put(parkingTicket, car);
        return parkingTicket;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return parkingTicketsAndCarsMap.remove(parkingTicket);
    }
}
