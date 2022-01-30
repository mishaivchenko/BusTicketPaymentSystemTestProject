package com.example.busticketsystem.BusTicketPaymentSystemTestProject.exception;

import com.example.busticketsystem.BusTicketPaymentSystemTestProject.exception.base.BusinessLogicException;

public class FlightIsOutToDayException extends BusinessLogicException {

    public FlightIsOutToDayException(String flight_id) {
        super("The flight with id = " + flight_id + " is out of date");
    }
}
