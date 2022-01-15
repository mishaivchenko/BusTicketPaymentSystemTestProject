package com.example.busticketsystem.BusTicketPaymentSystemTestProject.controller;

import com.example.busticketsystem.BusTicketPaymentSystemTestProject.dto.NewPaymentDTO;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Payment;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.service.PaymentService;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.providers.PaymentStatusProvider;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "User interface for Payment service")
@RequestMapping("/api/payments")
@RestController
public class PaymentController {

    private PaymentService paymentService;
    private PaymentStatusProvider paymentStatusProvider;

    @Autowired
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    @Autowired
    public void setPaymentStatusProvider(PaymentStatusProvider paymentStatusProvider) {
        this.paymentStatusProvider = paymentStatusProvider;
    }
    @PostMapping("/")
    public long createPayment(@RequestBody NewPaymentDTO paymentDTO){

        Payment payment = new Payment();
        payment.setStatus(String.valueOf(paymentStatusProvider.getNew()));
        payment.setOwner(paymentDTO.getOwner());
        payment.setPrice(paymentDTO.getSum());

        return paymentService.savePayment(payment).getId();
    }

}
