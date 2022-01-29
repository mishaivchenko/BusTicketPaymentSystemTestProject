package com.example.busticketsystem.BusTicketPaymentSystemTestProject.exception;

import com.example.busticketsystem.BusTicketPaymentSystemTestProject.exception.base.BusinessLogicException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TicketNotFoundException extends BusinessLogicException {

    public TicketNotFoundException(String ticketId) {
        super("couldn't find ticket with id = " + ticketId);
    }
}
