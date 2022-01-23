package com.example.busticketsystem.BusTicketPaymentSystemTestProject.controller;

import com.example.busticketsystem.BusTicketPaymentSystemTestProject.dto.OrderTicketDTO;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Ticket;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.facade.OrderTicketFacade;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "User interface for ticket management service")
@RequestMapping("/api/tickets")
@RestController
public class TicketManagementController {

    private OrderTicketFacade orderTicketFacade;

    @Autowired
    public void setOrderTicketFacade(OrderTicketFacade orderTicketFacade) {
        this.orderTicketFacade = orderTicketFacade;
    }

    @PostMapping("/")
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
