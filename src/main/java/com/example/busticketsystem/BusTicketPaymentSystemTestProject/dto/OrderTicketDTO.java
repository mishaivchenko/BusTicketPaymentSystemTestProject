package com.example.busticketsystem.BusTicketPaymentSystemTestProject.dto;

public class OrderTicketDTO {
    private final String initials;
    private final long flight_id;

    public OrderTicketDTO(String initials, long flight_id) {
        this.initials = initials;
        this.flight_id = flight_id;
    }

    public String getInitials() {
        return initials;
    }

    public long getFlight_id() {
        return flight_id;
    }
}
