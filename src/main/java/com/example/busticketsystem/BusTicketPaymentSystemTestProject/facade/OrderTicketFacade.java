package com.example.busticketsystem.BusTicketPaymentSystemTestProject.facade;

import com.example.busticketsystem.BusTicketPaymentSystemTestProject.dto.NewPaymentDTO;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Flight;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Payment;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Ticket;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.service.FlightService;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.service.PaymentService;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.service.TicketService;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.service.rest.RestIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderTicketFacade {
    private FlightService flightService;
    private TicketService ticketService;
    private PaymentService paymentService;
    private RestIntegrationService restIntegrationService;

    @Autowired
    public void setFlightService(FlightService flightService) {
        this.flightService = flightService;
    }
    @Autowired
    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }
    @Autowired
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    @Autowired
    public void setRestIntegrationService(RestIntegrationService restIntegrationService) {
        this.restIntegrationService = restIntegrationService;
    }

    public long orderTicket(long flightId, String initials){
        Ticket ticket = new Ticket();
        ticket.setOwner(initials);
        Ticket savedTicket = ticketService.saveTicket(ticket);
        Flight flight = flightService.getFlight(flightId);
        flight.addTicket(savedTicket);


        Long paymentId = restIntegrationService.createPayment(flight.getPrice(), initials);

        Payment payment = paymentService.getPayment(paymentId);
        savedTicket.setPayment(payment);

        flightService.saveFlight(flight);

        return savedTicket.getId();
    }
}
