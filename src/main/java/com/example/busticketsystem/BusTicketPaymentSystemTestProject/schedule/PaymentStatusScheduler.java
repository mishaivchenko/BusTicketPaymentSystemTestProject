package com.example.busticketsystem.BusTicketPaymentSystemTestProject.schedule;

import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Flight;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Payment;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Ticket;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.providers.PaymentStatus;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.providers.PaymentStatusProvider;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.service.FlightService;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class PaymentStatusScheduler {
    private PaymentService paymentService;
    private PaymentStatusProvider paymentStatusProvider;
    private FlightService flightService;

    @Autowired
    public void setFlightService(FlightService flightService) {
        this.flightService = flightService;
    }

    @Autowired
    public void setPaymentStatusProvider(PaymentStatusProvider paymentStatusProvider) {
        this.paymentStatusProvider = paymentStatusProvider;
    }

    @Autowired
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Scheduled(fixedRate = 6000)
    public void processPayments() {
        List<Payment> allPayments = paymentService.findAll();

        for (Payment payment : allPayments) {
            if (PaymentStatus.NEW.equals(payment.getStatus())) {
                payment.setStatus(paymentStatusProvider.getRandomStatus());

                if (PaymentStatus.FAILED.equals(payment.getStatus())) {
                    Ticket ticket = payment.getTicket();
                    Flight flight = ticket.getFlight();
                    flight.removeTicket(ticket);
                    payment.setStatus(PaymentStatus.CANCELLED);

                    flightService.saveFlight(flight);
                }

                paymentService.savePayment(payment);
            }
        }
    }
}
