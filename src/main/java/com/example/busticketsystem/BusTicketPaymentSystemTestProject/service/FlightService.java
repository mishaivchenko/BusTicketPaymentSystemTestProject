package com.example.busticketsystem.BusTicketPaymentSystemTestProject.service;

import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Flight;

import java.util.List;

public interface FlightService {
    List<Flight> findAll();

    Flight getFlight(Long flightId);

    void saveFlight(Flight flight);

    void updateFlight(Flight flight);
}
