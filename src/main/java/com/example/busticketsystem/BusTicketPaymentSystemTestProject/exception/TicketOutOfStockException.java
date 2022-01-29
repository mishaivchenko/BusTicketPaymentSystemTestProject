package com.example.busticketsystem.BusTicketPaymentSystemTestProject.exception;

import com.example.busticketsystem.BusTicketPaymentSystemTestProject.exception.base.BusinessLogicException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TicketOutOfStockException extends BusinessLogicException {

    public TicketOutOfStockException(String flightId) {
        super("Tickets for the flight id = " + flightId + "  are out of stock. ");
    }
}
