package com.example.busticketsystem.BusTicketPaymentSystemTestProject.dto;

import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Flight;

import java.util.Calendar;

public class FlightInfoDTO {

    private final Calendar date;
    private final int price;
    private final String to;
    private final String from;
    private final int ticket_count;

    public FlightInfoDTO(Flight flight) {
         this.date = flight.getDate();
         this.price = flight.getPrice();
         this.to = flight.getTo();
         this.from = flight.getFrom();
         this.ticket_count = flight.getCount() - flight.getTickets().size();
    }

    public Calendar getDate() {
        return date;
    }

    public int getPrice() {
        return price;
    }

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }

    public int getTicket_count() {
        return ticket_count;
    }
}
