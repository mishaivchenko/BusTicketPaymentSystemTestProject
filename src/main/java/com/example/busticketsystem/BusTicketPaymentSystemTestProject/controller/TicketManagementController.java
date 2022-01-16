package com.example.busticketsystem.BusTicketPaymentSystemTestProject.controller;

import com.example.busticketsystem.BusTicketPaymentSystemTestProject.dto.FlightInfoDTO;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.dto.FlightInfoWithPaymentStatusDTO;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.dto.NewPaymentDTO;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.dto.OrderTicketDTO;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Flight;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Payment;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Ticket;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.service.FlightService;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.service.PaymentService;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.service.TicketService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "User interface for ticket management service")
@RequestMapping("/api/flights")
@RestController
public class TicketManagementController {

    private RestTemplate restTemplate;
    private FlightService flightService;
    private TicketService ticketService;
    private PaymentService paymentService;

    @Autowired
    public void setFlightService(FlightService flightService) {
        this.flightService = flightService;
    }

    @Autowired
    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    @GetMapping("/")
    public List<FlightInfoDTO> getFlights() {

        List<Flight> flights = flightService.findAll();

        return flights.stream()
                .map(FlightInfoDTO::new)
                .collect(Collectors.toList());

    }


    @PostMapping("/")
    public long orderTicket(@RequestBody OrderTicketDTO orderTicketDTO) {
        final String RESOURCE_URL = "http://localhost:8080/api/payments/";

        Ticket ticket = new Ticket();
        ticket.setOwner(orderTicketDTO.getInitials());
        Ticket savedTicket = ticketService.saveTicket(ticket);
        Flight flight = flightService.getFlight(orderTicketDTO.getFlight_id());
        flight.addTicket(savedTicket);

        HttpEntity<NewPaymentDTO> request = new HttpEntity<>(new NewPaymentDTO(
                flight.getPrice(),
                orderTicketDTO.getInitials())
        );

        Long payment_id = restTemplate.postForObject(RESOURCE_URL, request, Long.class);

        Payment payment = paymentService.getPayment(payment_id);
        savedTicket.setPayment(payment);

        flightService.saveFlight(flight);


        return savedTicket.getId();
    }

    @GetMapping("/{ticket_id}")
    public FlightInfoWithPaymentStatusDTO getFlightInfo(@PathVariable long ticket_id) {

        Ticket ticket = ticketService.getTicket(ticket_id);
        Flight flight = ticket.getFlight();
        if (flight != null) {
            return new FlightInfoWithPaymentStatusDTO(flight, ticket.getPayment().getStatus());
        }
        return null;
    }


}
