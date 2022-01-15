package com.example.busticketsystem.BusTicketPaymentSystemTestProject.service.impl;

import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Flight;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.repository.FlightRepository;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FlightServiceImpl implements FlightService {

    private FlightRepository flightRepository;

    @Autowired
    public void setFlightRepository(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    @Override
    public Flight getFlight(Long flightId) {
        return flightRepository.findById(flightId).orElse(null);
    }

    @Override
    public void saveFlight(Flight flight) {
        flightRepository.save(flight);
    }

    @Override
    public void updateFlight(Flight flight) {
        flightRepository.save(flight);
    }
}
