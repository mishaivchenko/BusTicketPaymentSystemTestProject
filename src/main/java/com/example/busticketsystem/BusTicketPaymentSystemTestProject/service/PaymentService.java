package com.example.busticketsystem.BusTicketPaymentSystemTestProject.service;

import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Payment;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.providers.PaymentStatus;

import java.util.List;

public interface PaymentService {
    List<Payment> findAll();

    Payment getPayment(Long paymentId);

    Payment savePayment(Payment payment);

    void updatePayment(Payment payment);

    List<Payment> findByStatus(PaymentStatus status);
}
