package com.example.busticketsystem.BusTicketPaymentSystemTestProject.schedule;

import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Flight;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Payment;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Ticket;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.providers.PaymentStatus;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.service.FlightService;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.service.PaymentService;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.service.TicketService;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.service.rest.RestIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class PaymentStatusScheduler {

    private PaymentService paymentService;
    private FlightService flightService;
    private TicketService ticketServiceInCache;
    private RestIntegrationService restIntegrationService;

    private TicketService ticketServiceInDb;

    @Autowired
    public void setRestIntegrationService(RestIntegrationService restIntegrationService) {
        this.restIntegrationService = restIntegrationService;
    }

    @Autowired
    public void setFlightService(FlightService flightService) {
        this.flightService = flightService;
    }

    @Autowired
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Autowired
    public void setTicketServiceInCache(TicketService ticketServiceInCache) {
        this.ticketServiceInCache = ticketServiceInCache;
    }

    @Autowired
    public void setTicketServiceInDb(TicketService ticketServiceInDb) {
        this.ticketServiceInDb = ticketServiceInDb;
    }

    @Scheduled(fixedRate = 10000)
    public void processPayments() {
        List<Payment> allPayments = paymentService.findByStatus(PaymentStatus.NEW);

        for (Payment payment : allPayments) {

            payment.setStatus(restIntegrationService.getRandomPaymentStatus());

            if (PaymentStatus.FAILED.equals(payment.getStatus())) {
                processPaymentWithFailedStatus(payment);
            } else {
                paymentService.savePayment(payment);
            }

        }
    }

    private void processPaymentWithFailedStatus(Payment payment) {
        Ticket ticket = payment.getTicket();
        Flight flight = ticket.getFlight();

        ticket.setPayment(null);
        payment.setTicket(null);

        paymentService.savePayment(payment);
        ticketServiceInCache.saveTicket(ticket);

        Ticket savedTicket = ticketServiceInDb.saveTicket(ticket);

        flight.removeTicket(savedTicket);

        flightService.saveFlight(flight);
    }
}
