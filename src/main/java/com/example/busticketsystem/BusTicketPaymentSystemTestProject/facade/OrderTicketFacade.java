package com.example.busticketsystem.BusTicketPaymentSystemTestProject.facade;

import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Flight;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Payment;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Ticket;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.exception.EmptyInitialsException;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.exception.TicketOutOfStockException;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.service.FlightService;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.service.PaymentService;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.service.TicketService;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.service.rest.RestIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class OrderTicketFacade {
    private FlightService flightService;
    private TicketService ticketServiceInDb;
    private PaymentService paymentService;
    private RestIntegrationService restIntegrationService;
    private TicketService ticketServiceInCache;


    @Autowired
    public void setFlightService(FlightService flightService) {
        this.flightService = flightService;
    }

    @Autowired
    public void setTicketServiceInDb(TicketService ticketServiceInDb) {
        this.ticketServiceInDb = ticketServiceInDb;
    }

    @Autowired
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Autowired
    public void setRestIntegrationService(RestIntegrationService restIntegrationService) {
        this.restIntegrationService = restIntegrationService;
    }

    @Autowired
    public void setTicketServiceInCache(@Qualifier("ticketServiceInCache") TicketService ticketServiceInCache) {
        this.ticketServiceInCache = ticketServiceInCache;
    }


    public Ticket orderTicket(long flightId, String initials) {

        if (initials.isEmpty()) {
            throw new EmptyInitialsException();
        }

        Flight flight = flightService.getFlight(flightId);

        if (flight.getTickets().size() + ticketServiceInCache.getAll(flightId).size() >= flight.getCount()) {
            throw new TicketOutOfStockException(String.valueOf(flightId));
        }

        Ticket ticket = ticketServiceInCache.getTicketByFlightId(flightId).stream()
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(new Ticket());

        ticket.setOwner(initials);
        Ticket savedTicket = ticketServiceInDb.saveTicket(ticket);

        flight.addTicket(savedTicket);


        Long paymentId = restIntegrationService.createPayment(flight.getPrice(), initials);

        Payment payment = paymentService.getPayment(paymentId);
        savedTicket.setPayment(payment);

        flightService.saveFlight(flight);

        return savedTicket;
    }
}
