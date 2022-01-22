package com.example.busticketsystem.BusTicketPaymentSystemTestProject.controller;

import com.example.busticketsystem.BusTicketPaymentSystemTestProject.dto.NewPaymentDTO;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Payment;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.providers.PaymentStatusProvider;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.service.PaymentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Long> createPayment(@RequestBody NewPaymentDTO paymentDTO) {

        Payment payment = new Payment();
        payment.setStatus(paymentStatusProvider.getNew());
        payment.setOwner(paymentDTO.getOwner());
        payment.setPrice(paymentDTO.getSum());

        Payment savedPayment = paymentService.savePayment(payment);
        if (savedPayment != null){
            return new ResponseEntity<>(savedPayment.getId(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

}
