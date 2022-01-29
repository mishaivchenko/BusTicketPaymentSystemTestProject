package com.example.busticketsystem.BusTicketPaymentSystemTestProject.exception;

import com.example.busticketsystem.BusTicketPaymentSystemTestProject.exception.base.BusinessLogicException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmptyInitialsException extends BusinessLogicException {

    public EmptyInitialsException() {
        super("Initials must be not empty");
    }
}
