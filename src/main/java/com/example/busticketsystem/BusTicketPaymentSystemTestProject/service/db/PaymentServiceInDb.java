package com.example.busticketsystem.BusTicketPaymentSystemTestProject.service.db;

import com.example.busticketsystem.BusTicketPaymentSystemTestProject.entity.Payment;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.providers.PaymentStatus;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.repository.PaymentRepository;
import com.example.busticketsystem.BusTicketPaymentSystemTestProject.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceInDb implements PaymentService {

    private PaymentRepository paymentRepository;

    @Autowired
    public void setPaymentRepository(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment getPayment(Long paymentId) {
        return paymentRepository.findById(paymentId).orElse(null);
    }

    @Override
    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public void updatePayment(Payment payment) {
        paymentRepository.save(payment);
    }

    @Override
    public List<Payment> findByStatus(PaymentStatus status) {
        return paymentRepository.findByStatus(status);
    }
}
