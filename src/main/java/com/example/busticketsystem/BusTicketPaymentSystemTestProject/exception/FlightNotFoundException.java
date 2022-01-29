package com.example.busticketsystem.BusTicketPaymentSystemTestProject.exception;

import com.example.busticketsystem.BusTicketPaymentSystemTestProject.exception.base.BusinessLogicException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FlightNotFoundException extends BusinessLogicException {

    public FlightNotFoundException(String ticketId) {
        super("couldn't find flight with id = " + ticketId);
    }
}
