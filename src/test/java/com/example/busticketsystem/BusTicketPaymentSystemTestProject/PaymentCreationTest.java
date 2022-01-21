package com.example.busticketsystem.BusTicketPaymentSystemTestProject;

import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Flight;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Payment;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Ticket;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.providers.PaymentStatusProvider;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.repository.FlightRepository;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.repository.PaymentRepository;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.repository.TicketRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.Calendar;

@DataJpaTest
@ComponentScan("com.example.busticketsystem.BusTicketPaymentSystemTestProject.*")
public class PaymentCreationTest {

    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PaymentStatusProvider paymentStatusProvider;


    @Test
    public void createFlightTest_TicketCountAfterSaveMustBeEqualToTicketCountBeforeSave() {
        Flight flight = new Flight();
        flight.setDate(Calendar.getInstance());
        flight.setFrom("From");
        flight.setTo("To");
        flight.setPrice(20);

        flight.addTicket(new Ticket());
        flight.addTicket(new Ticket());
        flight.addTicket(new Ticket());
        flight.addTicket(new Ticket());

        flightRepository.save(flight);

        Assertions.assertEquals(4, ticketRepository.findAll().size());

    }

    @Test
    public void createPaymentTest_TicketOwnerNameMustBeTheSame() {
        Payment payment = new Payment();
        payment.setStatus(paymentStatusProvider.getRandomStatus());
        Ticket ticket = new Ticket();

        String ownerName = "Misha";
        ticket.setOwner(ownerName);

        payment.setTicket(ticket);

        paymentRepository.save(payment);

        String owner = ticketRepository.findAll().get(0).getOwner();

        Assertions.assertEquals(ownerName, owner);
    }
}
