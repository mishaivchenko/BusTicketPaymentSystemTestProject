package com.example.busticketsystem.BusTicketPaymentSystemTestProject.providers;


import org.springframework.stereotype.Component;

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
                .get();
    }

    public PaymentStatus getNew() {
        return PaymentStatus.NEW;
    }
}
