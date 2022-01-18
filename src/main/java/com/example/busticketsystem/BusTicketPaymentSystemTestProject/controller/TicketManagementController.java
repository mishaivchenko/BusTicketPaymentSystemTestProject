package com.example.busticketsystem.BusTicketPaymentSystemTestProject.controller;

import com.example.busticketsystem.BusTicketPaymentSystemTestProject.dto.FlightInfoDTO;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.dto.FlightInfoWithPaymentStatusDTO;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.dto.OrderTicketDTO;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Flight;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Ticket;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.facade.OrderTicketFacade;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.service.FlightService;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.service.TicketService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "User interface for ticket management service")
@RequestMapping("/api/flights")
@RestController
public class TicketManagementController {


    private FlightService flightService;
    private TicketService ticketService;
    private OrderTicketFacade orderTicketFacade;

    @Autowired
    public void setOrderTicketFacade(OrderTicketFacade orderTicketFacade) {
        this.orderTicketFacade = orderTicketFacade;
    }

    @Autowired
    public void setFlightService(FlightService flightService) {
        this.flightService = flightService;
    }

    @Autowired
    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
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

        return orderTicketFacade.orderTicket(
                orderTicketDTO.getFlight_id(),
                orderTicketDTO.getInitials()
        );
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
