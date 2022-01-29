package com.example.busticketsystem.BusTicketPaymentSystemTestProject.service;

import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Flight;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.exception.FlightNotFoundException;

import java.util.List;

public interface FlightService {
    List<Flight> findAll();

    Flight getFlight(Long flightId) throws FlightNotFoundException;

    void saveFlight(Flight flight);

    void updateFlight(Flight flight);
}
