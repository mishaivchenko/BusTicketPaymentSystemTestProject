package com.example.busticketsystem.BusTicketPaymentSystemTestProject.controller.ticketMagnagmentSystem;

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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "User interface for Flight service")
@RequestMapping("/api/flights")
@RestController
public class FlightController {

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
    public void setTicketService(@Qualifier("ticketServiceInDb") TicketService ticketService) {
        this.ticketService = ticketService;
    }


    @GetMapping("/{ticket_id}")
    public ResponseEntity<FlightInfoWithPaymentStatusDTO> getFlightInfoByTicketId(@PathVariable long ticket_id) {

        Ticket ticket = ticketService.getTicket(ticket_id);
        Flight flight = ticket.getFlight();

        if (flight == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new FlightInfoWithPaymentStatusDTO(flight, ticket.getPayment().getStatus()), HttpStatus.OK);
    }

    @GetMapping("/")
    public List<FlightInfoDTO> getFlights() {

        List<Flight> flights = flightService.findAll();

        return flights.stream()
                .map(FlightInfoDTO::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/{flight_id}/")
    public ResponseEntity<Long> orderTicket(@RequestBody OrderTicketDTO orderTicketDTO) {

        Ticket ticket = orderTicketFacade.orderTicket(
                orderTicketDTO.getFlight_id(),
                orderTicketDTO.getInitials()
        );

        if (ticket == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ticket.getId(), HttpStatus.CREATED);
    }

}
