package com.example.busticketsystem.BusTicketPaymentSystemTestProject.providers;


import java.util.Arrays;
import java.util.Random;

public class PaymentStatusProvider {

    public PaymentStatusProvider() {
    }

    private final Random r = new Random();

    public PaymentStatus getRandomStatus() {
        PaymentStatus paymentStatus = Arrays.stream(PaymentStatus.values())
                .skip(r.nextInt(PaymentStatus.values().length))
                .findFirst()
                .orElse(PaymentStatus.NEW);
        return paymentStatus;
    }

    public PaymentStatus getNew() {
        return PaymentStatus.NEW;
    }
}
