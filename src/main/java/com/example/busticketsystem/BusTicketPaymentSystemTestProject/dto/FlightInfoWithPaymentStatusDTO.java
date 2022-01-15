package com.example.busticketsystem.BusTicketPaymentSystemTestProject.dto;

import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Flight;

public class FlightInfoWithPaymentStatusDTO extends FlightInfoDTO {
    private final String status;

    public FlightInfoWithPaymentStatusDTO(Flight flight, String status) {
        super(flight);
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
