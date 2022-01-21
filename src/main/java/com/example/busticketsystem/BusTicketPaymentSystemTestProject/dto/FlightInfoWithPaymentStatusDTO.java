package com.example.busticketsystem.BusTicketPaymentSystemTestProject.dto;

import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Flight;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.providers.PaymentStatus;

public class FlightInfoWithPaymentStatusDTO extends FlightInfoDTO {
    private final PaymentStatus status;

    public FlightInfoWithPaymentStatusDTO(Flight flight, PaymentStatus status) {
        super(flight);
        this.status = status;
    }

    public PaymentStatus getStatus() {
        return status;
    }
}
