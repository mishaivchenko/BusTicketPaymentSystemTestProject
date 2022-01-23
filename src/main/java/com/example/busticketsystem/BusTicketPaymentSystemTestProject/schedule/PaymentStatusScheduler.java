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

    @Scheduled(fixedRate = 6000)
    public void processPayments() {
        List<Payment> allPayments = paymentService.findByStatus(PaymentStatus.NEW);

        for (Payment payment : allPayments) {
            if (PaymentStatus.NEW.equals(payment.getStatus())) {
                payment.setStatus(restIntegrationService.getRandomPaymentStatus());

                if (PaymentStatus.FAILED.equals(payment.getStatus())) {
                    Ticket ticket = payment.getTicket();
                    Flight flight = ticket.getFlight();
                    ticket.setPayment(null);
                    payment.setTicket(null);
                    ticketServiceInCache.saveTicket(ticket);
                    flight.removeTicket(ticket);
                    flightService.saveFlight(flight);
                }

                paymentService.savePayment(payment);
            }
        }
    }
}
