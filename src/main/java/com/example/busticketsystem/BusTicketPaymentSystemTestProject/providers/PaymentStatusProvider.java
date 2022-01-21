package com.example.busticketsystem.BusTicketPaymentSystemTestProject.providers;


import java.util.Arrays;
import java.util.Random;
import java.util.function.Predicate;

public class PaymentStatusProvider {

    private final Predicate<PaymentStatus> notCancelledPaymentStatusPredicate = (e) -> !e.equals(PaymentStatus.CANCELLED);
    public PaymentStatusProvider() {
    }

    private final Random r = new Random();

    public PaymentStatus getRandomStatus() {
        return Arrays.stream(PaymentStatus.values())
                .filter(notCancelledPaymentStatusPredicate)
                .skip(r.nextInt(PaymentStatus.values().length))
                .findFirst()
                .orElse(PaymentStatus.NEW);
    }

    public PaymentStatus getNew() {
        return PaymentStatus.NEW;
    }
}
