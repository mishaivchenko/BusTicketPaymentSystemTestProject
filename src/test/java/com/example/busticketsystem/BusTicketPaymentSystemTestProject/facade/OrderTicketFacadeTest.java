package com.example.busticketsystem.BusTicketPaymentSystemTestProject.facade;

import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Flight;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Ticket;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.exception.EmptyInitialsException;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.exception.FlightIsOutToDayException;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.exception.TicketOutOfStockException;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.exception.base.BusinessLogicException;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.service.FlightService;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.service.PaymentService;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.service.TicketService;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.service.rest.RestIntegrationService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.Period;

import static org.mockito.Mockito.when;

public class OrderTicketFacadeTest {
    @Mock
    private FlightService flightService;
    @Mock
    private TicketService ticketServiceInDb;
    @Mock
    private PaymentService paymentService;
    @Mock
    private RestIntegrationService restIntegrationService;
    @Mock
    private TicketService ticketServiceInCache;

    @InjectMocks
    OrderTicketFacade orderTicketFacade;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = EmptyInitialsException.class)
    public void orderTicketWithEmptyInitialsTest() throws BusinessLogicException {
        orderTicketFacade.orderTicket(1, "");
    }

    @Test(expected = TicketOutOfStockException.class)
    public void orderOutOfStockTicketTest() throws BusinessLogicException {
        Ticket ticket = new Ticket();
        Ticket ticket1 = new Ticket();
        ticket.setId(1);
        ticket.setId(2);


        Flight flight = new Flight();
        flight.setId(1);
        flight.setCount(2);
        flight.addTicket(ticket);
        flight.addTicket(ticket1);

        when(flightService.getFlight(flight.getId())).thenReturn(flight);

        orderTicketFacade.orderTicket(flight.getId(), "Ivan Ivanovich");
    }

    @Test(expected = FlightIsOutToDayException.class)
    public void orderOutOfDateTicketTest() throws BusinessLogicException {
        Flight flight = new Flight();
        flight.setId(1L);
        flight.setCount(1);
        flight.setDate(LocalDate.now().minus(Period.ofYears(1)));

        when(flightService.getFlight(flight.getId())).thenReturn(flight);

        orderTicketFacade.orderTicket(flight.getId(), "Ivan Ivanovich");

    }
}
