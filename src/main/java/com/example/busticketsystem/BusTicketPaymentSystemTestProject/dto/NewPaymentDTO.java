package com.example.busticketsystem.BusTicketPaymentSystemTestProject.dto;

public class NewPaymentDTO {
    private final int sum;
    private final String owner;

    public NewPaymentDTO(int sum, String owner) {
        this.sum = sum;
        this.owner = owner;
    }

    public int getSum() {
        return sum;
    }

    public String getOwner() {
        return owner;
    }
}
