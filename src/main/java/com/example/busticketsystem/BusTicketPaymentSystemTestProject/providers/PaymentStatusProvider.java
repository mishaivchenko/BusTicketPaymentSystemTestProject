package com.example.busticketsystem.BusTicketPaymentSystemTestProject.providers;


import java.util.Arrays;
import java.util.Random;

public class PaymentStatusProvider {

    enum PaymentStatus {
        NEW, FAILED, DONE
    }

    public PaymentStatusProvider() {
    }

    private final Random r = new Random();

    public PaymentStatus getRandomStatus() {
        return Arrays.stream(PaymentStatus.values())
                .skip(r.nextInt(PaymentStatus.values().length))
                .findFirst()
                .orElse(PaymentStatus.NEW);
    }

    public PaymentStatus getNew() {
        return PaymentStatus.NEW;
    }
}
