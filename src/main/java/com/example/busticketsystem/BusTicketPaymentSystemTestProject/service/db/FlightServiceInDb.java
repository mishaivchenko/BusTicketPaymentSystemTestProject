package com.example.busticketsystem.BusTicketPaymentSystemTestProject.service.db;

import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Flight;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.exception.FlightNotFoundException;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.repository.FlightRepository;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceInDb implements FlightService {

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
    public Flight getFlight(Long flightId) throws FlightNotFoundException {
        return flightRepository.findById(flightId)
                .orElseThrow(() -> new FlightNotFoundException(String.valueOf(flightId)));
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
