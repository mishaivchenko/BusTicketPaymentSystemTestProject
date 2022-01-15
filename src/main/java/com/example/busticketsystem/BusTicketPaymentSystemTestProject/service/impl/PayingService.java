package com.example.busticketsystem.BusTicketPaymentSystemTestProject.service.impl;

import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Payment;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Ticket;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.providers.PaymentStatusProvider;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayingService {

    private PaymentService paymentService;
    private PaymentStatusProvider paymentStatusProvider;

    @Autowired
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    @Autowired
    public void setPaymentStatusService(PaymentStatusProvider paymentStatusProvider) {
        this.paymentStatusProvider = paymentStatusProvider;
    }

    public long doPay(String owner, int sum){
        Ticket ticket = new Ticket();
        ticket.setOwner(owner);
        //ticket.set

        Payment payment = new Payment();

        return 0;
    }


}
