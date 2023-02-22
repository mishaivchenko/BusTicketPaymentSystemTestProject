package com.example.busticketsystem.BusTicketPaymentSystemTestProject.providers;


import java.util.Arrays;
import java.util.Random;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class PaymentStatusProvider {

    public PaymentStatusProvider() {
    }

    private final Random r = new Random();

    private final Supplier<PaymentStatus> paymentStatusSupplierFunction =
            () -> Arrays.stream(
                    PaymentStatus.values())
                    .skip(r.nextInt(PaymentStatus.values().length))
                    .findFirst()
                    .orElse(PaymentStatus.NEW);

    public PaymentStatus getRandomStatus() {
        return paymentStatusSupplierFunction.get();
    }

    public PaymentStatus getNew() {
        return PaymentStatus.NEW;
    }
}
