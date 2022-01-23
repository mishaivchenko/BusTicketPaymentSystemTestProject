package com.example.busticketsystem.BusTicketPaymentSystemTestProject.service;

import com.example.busticketsystem.BusTicketPaymentSystemTestProject.exception.FlightNotFoundException;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.repository.FlightRepository;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.service.db.FlightServiceInDb;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

public class FlightServiceTest {
    @Mock
    FlightRepository flightRepository;

    @InjectMocks
    FlightServiceInDb flightService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = FlightNotFoundException.class)
    public void whenTicketIdIsIncorrectThenThrowTicketNotFoundException() {
        when(flightRepository.getOne(0L)).thenReturn(null);

        flightService.getFlight(0L);
    }


}
