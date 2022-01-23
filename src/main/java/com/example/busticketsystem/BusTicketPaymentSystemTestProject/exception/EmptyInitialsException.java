package com.example.busticketsystem.BusTicketPaymentSystemTestProject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmptyInitialsException extends RuntimeException {

    public EmptyInitialsException() {
        super("Initials must be not empty");
    }
}
