package com.example.busticketsystem.BusTicketPaymentSystemTestProject.providers;

public enum PaymentStatus {
    NEW("NEW"), FAILED("FAILED"), DONE("DONE");
    private final String value;

    PaymentStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
